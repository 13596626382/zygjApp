package com.tx.zygj.ui.complete.wallet

import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.getCompatDrawable
import com.llx.common.util.intentParcelableExtras
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.bean.PaySuccessBean
import com.tx.zygj.databinding.ActivityWalletCompleteOrderBinding
import com.tx.zygj.util.SunmiPrintHelper

//充值完成订单
class WalletCompleteOrderActivity :
    BaseActivity<ActivityWalletCompleteOrderBinding>(R.layout.activity_wallet_complete_order) {
    private var memberManageBean: MemberManageBean? = null
    private var paySuccessBean: PaySuccessBean? = null
    override fun initData() {
        binding.titleBarView.setOnBack(this)

        memberManageBean = intentParcelableExtras(CommonConstant.MEMBER_BEAN)
        paySuccessBean = intentParcelableExtras("paySuccessBean")

        binding.memberBeam = memberManageBean
        binding.paySuccessBean = paySuccessBean
        binding.repair.setOnClickListener {
            binding.repair.isEnabled = false
            binding.repair.background = getCompatDrawable(R.drawable.fillet_dbdbdb_22)
            SunmiPrintHelper.sendWalletRawData(paySuccessBean, memberManageBean)
        }
    }
}