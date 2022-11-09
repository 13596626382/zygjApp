package com.tx.zygj.ui.member.information

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.intentIntExtras
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.startActivity
import com.llx.common.util.toast
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityMemberInformationBinding
import com.tx.zygj.ui.car.manage.CarBrandManageActivity
import com.tx.zygj.ui.cashier.fast.FastCashierActivity
import com.tx.zygj.ui.cashier.refueling.RefuelingCashierActivity
import com.tx.zygj.ui.member.consume.MemberConsumeActivity
import com.tx.zygj.ui.member.register.MemberRegisterActivity
import com.tx.zygj.ui.member.reset.MemberRechargeRecordActivity
import com.tx.zygj.ui.wallet.WalletRechargeActivity

/**
 * 会员信息
 */
class MemberInformationActivity :
    BaseActivity<ActivityMemberInformationBinding>(R.layout.activity_member_information) {
    private val model: MemberInformationViewModel by viewModels()
    override fun initData() {
        binding.titleBar.setOnBack(this)
        model.getMemBerMsg(intentIntExtras(CommonConstant.MEMBER_ID))
        binding.cashier.setOnClickListener {
            startActivity<RefuelingCashierActivity>(CommonConstant.MEMBER_BEAN to binding.memberBean) //加油收银
        }
        binding.fastCashier.setOnSingleClickListener {
            startActivity<FastCashierActivity>() //快速收银
        }
        binding.shopCashier.setOnClickListener {
            toast("功能开发中")
            return@setOnClickListener
//            startActivity<ShopActivity>() //积分商品
        }
        binding.waller.setOnClickListener {
            startActivity<WalletRechargeActivity>(CommonConstant.MEMBER_BEAN to binding.memberBean) //钱包充值
        }
        binding.integralExchange.setOnClickListener {
            toast("功能开发中")
            return@setOnClickListener
//            startActivity<ShopActivity>() //积分商品
        }
        binding.carManage.setOnSingleClickListener {
            if (binding.memberBean?.phone == null) {
                toast("用户没设置手机号")
                return@setOnSingleClickListener
            }
            startActivity<CarBrandManageActivity>(CommonConstant.MEMBER_PHONE to binding.memberBean?.phone) //车牌管理
        }
        binding.modify.setOnSingleClickListener {
            startActivity<MemberRegisterActivity>(
                CommonConstant.INTENT_TITLE to "修改信息",
                CommonConstant.MEMBER_BEAN to binding.memberBean
            ) //修改信息
        }
        binding.consume.setOnSingleClickListener {
            startActivity<MemberConsumeActivity>(CommonConstant.MEMBER_PHONE to binding.memberBean?.phone) //消费记录
        }
        binding.recharge.setOnSingleClickListener {
            startActivity<MemberRechargeRecordActivity>(CommonConstant.MEMBER_PHONE to binding.memberBean?.phone) //充值记录
        }
        binding.exchange.setOnSingleClickListener {
            toast("功能开发中")
            return@setOnSingleClickListener
//            startActivity<MemberExchangeRecordActivity>() //兑换记录
        }
        binding.integral.setOnSingleClickListener {
            toast("功能开发中")
            return@setOnSingleClickListener
//            startActivity<MemberIntegralRecordActivity>() //积分记录
        }

        model.memberManageBean.observe(this) {
            binding.memberBean = it
        }
    }

    override fun update() {
        model.getMemBerMsg(intentIntExtras(CommonConstant.MEMBER_ID))
    }
}