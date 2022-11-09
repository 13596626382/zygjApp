package com.tx.zygj.ui.handover

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityHandoverBinding
import com.tx.zygj.dialog.power.PowerDialogFragment
import com.tx.zygj.util.SunmiPrintHelper

/**
 * 交班信息
 */
class HandoverActivity : BaseActivity<ActivityHandoverBinding>(R.layout.activity_handover) {
    private val model: HandoverViewModel by viewModels()
    private val mAdapter by lazy { HandoverAdapter() }
    private lateinit var popUpView: LoadingPopupView
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.oilName.text = CommonConstant.getUserInfo()?.gasName
        binding.operation.text = stringBuild("操作员：", CommonConstant.getUserInfo()?.name)
        binding.time.text = stringBuild(
            CommonConstant.getUserInfo()?.startTime?.toYMDHMSDate(),
            " 至 ",
            toCurrentYMDHMSDate()
        )
        popUpView = showLoadingDialog("打印交班结果")
        binding.shiftHandover.showConfirmDialog("交班", "是否交班") {
            val fragment = PowerDialogFragment()
            fragment.onPassWordCorrect = {
                popUpView.show()
                SunmiPrintHelper.sendShiftHandoverRawData(binding.handoverBean)
                model.logout(popUpView)
            }
            fragment.show(supportFragmentManager, CommonConstant.DIALOG_FRAGMENT)

        }

        binding.recyclerView.bind(mAdapter) {
            setEmptyView(layoutInflater.inflate(R.layout.view_empty, binding.recyclerView, false))
        }
        model.getExchange()
        model.handoverBean.observe(this) {
            binding.handoverBean = it
            mAdapter.setList(it.oleicList)
        }

    }
}