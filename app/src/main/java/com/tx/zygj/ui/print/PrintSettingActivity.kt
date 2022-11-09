package com.tx.zygj.ui.print

import android.view.View
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.bean.PrintBean
import com.llx.common.custom.GlideEngine
import com.llx.common.util.addAfterTextChanged
import com.llx.common.util.getCompatDrawable
import com.llx.common.util.loadUrl
import com.llx.common.util.setOnSingleClickListener
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityPrintSettingBinding
import com.tx.zygj.util.SunmiPrintHelper

/**
 * 打印设置
 */
class PrintSettingActivity :
    BaseActivity<ActivityPrintSettingBinding>(R.layout.activity_print_setting) {
    private val model: PrintSettingViewModel by viewModels()
    private var printBean: PrintBean? = null
    override fun initData() {
        binding.titleBar.setOnBack(this)
        printBean = CommonConstant.getPrintBean()

        binding.printBean = printBean

        binding.printLogo.setOnCheckedChangeListener { _, isChecked ->
            printBean?.logoStatus = if (isChecked) 1 else 0
        }

        binding.printCode.setOnCheckedChangeListener { _, isChecked ->
            printBean?.twoCodeStatus = if (isChecked) 1 else 0
        }
        binding.shopName.setOnCheckedChangeListener { _, isChecked ->
            printBean?.shopStatus = if (isChecked) 1 else 0
        } //店铺名
        binding.receiptTitle.setOnCheckedChangeListener { _, isChecked ->
            printBean?.titleStatus = if (isChecked) 1 else 0
        } //小票标题
        binding.receiptType.setOnCheckedChangeListener { _, isChecked ->
            printBean?.typeStatus = if (isChecked) 1 else 0
        } //小票类型
        binding.receiptTail.setOnCheckedChangeListener { _, isChecked ->
            printBean?.footnoteStatus = if (isChecked) 1 else 0
        } //小票脚

        binding.orderNo.setOnCheckedChangeListener { _, isChecked ->
            printBean?.orderNoStatus = if (isChecked) 1 else 0
        } //订单编号

        binding.memberName.setOnCheckedChangeListener { _, isChecked ->
            printBean?.memberNameStatus = if (isChecked) 1 else 0
        } //会员名
        binding.memberPhone.setOnCheckedChangeListener { _, isChecked ->
            printBean?.phoneStatus = if (isChecked) 1 else 0
        } //会员手机号
        binding.memberType.setOnCheckedChangeListener { _, isChecked ->
            printBean?.memberTypeStatus = if (isChecked) 1 else 0
        } //会员类型
        binding.memberBalance.setOnCheckedChangeListener { _, isChecked ->
            printBean?.balanceStatus = if (isChecked) 1 else 0
        } //会员账户余额
        binding.memberIntegral.setOnCheckedChangeListener { _, isChecked ->
            printBean?.integralStatus = if (isChecked) 1 else 0
        } //会员积分

        binding.operationDate.setOnCheckedChangeListener { _, isChecked ->
            printBean?.operationStatus = if (isChecked) 1 else 0
        } //操作时间
        binding.gasAddress.setOnCheckedChangeListener { _, isChecked ->
            printBean?.addressStatus = if (isChecked) 1 else 0
        } //加油站地址
        binding.gasPhone.setOnCheckedChangeListener { _, isChecked ->
            printBean?.contactStatus = if (isChecked) 1 else 0
        } //加油站手机号

        binding.receiptTitleContent.addAfterTextChanged {
            printBean?.titleContent = this
        }
        binding.receiptTailContent.addAfterTextChanged {
            printBean?.footnote = this
        }

        binding.testPrint.setOnSingleClickListener {
            SunmiPrintHelper.testPrint(printBean)
        }
        binding.ok.setOnSingleClickListener {
            model.updatePrintSettings(printBean)
        }

        binding.addQrCode.setOnSingleClickListener {
            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine())
                .setMaxSelectNum(1)
                .setMinSelectNum(1)
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>?) {
                        binding.addQrCode.loadUrl(result!![0].path)

                    }

                    override fun onCancel() {

                    }
                })
        }

        binding.addLogo.setOnSingleClickListener {
            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine())
                .setMaxSelectNum(1)
                .setMinSelectNum(1)
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>?) {
                        binding.addLogo.loadUrl(result!![0].path)
                        printBean?.logo = result[0].realPath
                    }

                    override fun onCancel() {

                    }
                })
        }
        if (printBean == null) {
            binding.textView14.visibility = View.VISIBLE
            binding.ok.isEnabled = false
            binding.ok.background = getCompatDrawable(R.drawable.fillet_dbdbdb_22)
            binding.testPrint.isEnabled = false
            binding.testPrint.background = getCompatDrawable(R.drawable.fillet_dbdbdb_22)
        }
        binding.textView14.setOnSingleClickListener {
            binding.progressBae.visibility = View.VISIBLE
            model.getPrintSettings()
        }
        model.printBean.observe(this) {
            printBean = it
            binding.printBean = it
        }
        model.requestResult.observe(this) {
            binding.textView14.visibility = if (it) View.GONE else View.VISIBLE
            binding.progressBae.visibility = View.GONE
            binding.ok.isEnabled = it
            binding.ok.background =
                getCompatDrawable(if (it) R.drawable.change_fc664a_f38b26_22 else R.drawable.fillet_dbdbdb_22)
            binding.testPrint.isEnabled = it
            binding.testPrint.background =
                getCompatDrawable(if (it) R.drawable.change_fc664a_f38b26_22 else R.drawable.fillet_dbdbdb_22)

        }
    }
}