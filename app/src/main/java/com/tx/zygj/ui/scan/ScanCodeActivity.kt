package com.tx.zygj.ui.scan

import android.Manifest
import android.content.Intent
import androidx.activity.viewModels
import com.king.zxing.DefaultCameraScan
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.impl.LoadingPopupView
import com.permissionx.guolindev.PermissionX
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivitySacnCodeBinding
import com.tx.zygj.ui.member.information.MemberInformationActivity

/**
 * 扫二维码
 */
class ScanCodeActivity : BaseActivity<ActivitySacnCodeBinding>(R.layout.activity_sacn_code) {
    private lateinit var mCameraScan: DefaultCameraScan
    private val model: ScanCodeViewModel by viewModels()
    private lateinit var popupView: LoadingPopupView
    override fun initData() {
        popupView = showLoadingDialog("查询中")
        mCameraScan = DefaultCameraScan(this, binding.previewView)
        mCameraScan.apply {
            setPlayBeep(true)
            setVibrate(true)
            setOnScanResultCallback {
                logE(it.text)
                if (intentBooleanExtras(CommonConstant.SCAN_TYPE)) {
                    val intent = Intent()
                    intent.putExtra(CommonConstant.SCAN_CODE, it.text)
                    setResult(CommonConstant.REQUEST_CODE, intent)
                    onBack()
                } else {
                    if (it.text.startsWith("phone")) {
                        popupView.show()
                        model.findByPhone(it.text.substring(5, it.text.length))
                    } else {
                        toast("无法识别的二维码")
                    }
                }
                setAnalyzeImage(false)
                true
            }

        }
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.ivFlashlight.setOnClickListener {
            val isTorch = mCameraScan.isTorchEnabled
            mCameraScan.enableTorch(!isTorch)
            binding.ivFlashlight.isSelected = !isTorch
        }
        model.memberManageBean.observe(this) {
            popupView.dismiss()
            startActivity<MemberInformationActivity>(CommonConstant.MEMBER_ID to it.id)
            onBack()
        }
        checkPermission()
    }

    private fun checkPermission() {
        PermissionX.init(this)
            .permissions(Manifest.permission.CAMERA)
            .request { allGranted, _, _ ->
                if (allGranted) {
                    mCameraScan.startCamera()
                } else {
                    toast("您拒绝了相机权限，无法使用扫码功能")
                }
            }
    }

}