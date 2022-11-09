package com.tx.zygj.ui.complete.collection

import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.getCompatDrawable
import com.llx.common.util.intentParcelableExtras
import com.llx.common.util.stringBuild
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.bean.PaySuccessBean
import com.tx.zygj.databinding.ActivityCompleteOrderBinding
import com.tx.zygj.util.SunmiPrintHelper

//加油完成订单
class CompleteOrderActivity :
    BaseActivity<ActivityCompleteOrderBinding>(R.layout.activity_complete_order) {
    private var memberManageBean: MemberManageBean? = null
    private var paySuccessBean: PaySuccessBean? = null
    override fun initData() {
        binding.titleBarView.setOnBack(this)

        memberManageBean = intentParcelableExtras(CommonConstant.MEMBER_BEAN)
        paySuccessBean = intentParcelableExtras("paySuccessBean")
        binding.memberBeam = memberManageBean
        binding.paySuccessBean = paySuccessBean
        binding.operator.text = CommonConstant.getUserInfo()?.name
        binding.oilType.text = stringBuild(
            paySuccessBean?.model, "#", when (paySuccessBean?.typeId) {
                1 -> "汽油"
                2 -> "柴油"
                else -> "天然气"
            }
        )
        binding.repair.setOnClickListener {
            binding.repair.isEnabled = false
            binding.repair.background = getCompatDrawable(R.drawable.fillet_dbdbdb_22)
            SunmiPrintHelper.sendCashierRawData(paySuccessBean, memberManageBean)
        }
        CommonConstant.isPaySuccess = true
    }
}