package com.tx.zygj.ui.order.wallet

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.bean.OilerBean
import com.tx.zygj.bean.TtsBean
import com.tx.zygj.bean.WalletRechargeBean
import com.tx.zygj.databinding.ActivityWalletOrderBinding
import com.tx.zygj.dialog.order.wallet.WalletOrderDialogFragment
import com.tx.zygj.ui.complete.wallet.WalletCompleteOrderActivity
import com.tx.zygj.ui.scan.ScanCodeActivity
import com.tx.zygj.util.SunmiPrintHelper
import org.greenrobot.eventbus.EventBus

//钱包充值订单页
class WalletOrderActivity :
    BaseActivity<ActivityWalletOrderBinding>(R.layout.activity_wallet_order) {

    private val model: WalletOrderViewModel by viewModels()
    private lateinit var loadDialog: LoadingPopupView
    private lateinit var mWalletOrderDialogFragment: WalletOrderDialogFragment
    private var memberManageBean: MemberManageBean? = null
    private var rechargeActivityListBean = ArrayList<WalletRechargeBean.RechargeActivityBean>()
    private var rechargeActivityBean: WalletRechargeBean.RechargeActivityBean? = null
    private var oilerBean: OilerBean? = null //加油员

    private var price = "" //支付金额
    private var orderNo = "" //订单号
    private var cardType = 0 //充值类型

    override fun initData() {
        binding.titleBarView.setOnBack(this)
        loadDialog = showLoadingDialog("生成订单中")
        memberManageBean = intentParcelableExtras(CommonConstant.MEMBER_BEAN)
        oilerBean = intentParcelableExtras("oilerBean")
        price = intentStringExtras("price")!!
        cardType = intentIntExtras("cardType")
        binding.memberBeam = memberManageBean
        binding.ordinaryLayout.visibility = View.VISIBLE
        binding.price.text = price
        binding.operator.text = oilerBean?.name
        binding.type.text = when (cardType) {
            0 -> "汽油卡"
            1 -> "柴油卡"
            2 -> "通用钱包"
            else -> "天然气"
        }

        binding.settle.setOnSingleClickListener {
            loadDialog.show()
            model.findOrderNo(
                cardType,
                memberManageBean?.phone,
                price.toDouble(),
                give = rechargeActivityBean?.give,
                giveIntegral = rechargeActivityBean?.giveIntegral
            )
        }

        binding.choiceActivity.setOnSingleClickListener {
            mWalletOrderDialogFragment = WalletOrderDialogFragment()
            mWalletOrderDialogFragment.withArguments(
                "rechargeActivityBean" to rechargeActivityListBean,
                "price" to price
            )
            mWalletOrderDialogFragment.onActivityClickListener = {
//                model.getRechargeActivityDetails(it.id, price)
                binding.activity.text = it.activityName
                binding.activityGive.visibility = View.VISIBLE
                binding.givePrice.text = it.give.toString()
                binding.giveIntegral.text = it.giveIntegral.toString()
                rechargeActivityBean = it
            }
            mWalletOrderDialogFragment.show(supportFragmentManager, CommonConstant.DIALOG_FRAGMENT)
        }

        model.apply {
            getRechargeActivity(memberManageBean?.id)
            requestOrderNo.observe(this@WalletOrderActivity) {
                loadDialog.dismiss()
                orderNo = it
                startActivityForResult(
                    Intent(mContext, ScanCodeActivity::class.java).putExtra(
                        CommonConstant.SCAN_TYPE, true
                    ), CommonConstant.REQUEST_CODE
                )
            }
            paySuccessBean.observe(this@WalletOrderActivity) {
                if (binding.tickets.isChecked) {
                    SunmiPrintHelper.sendWalletRawData(it, memberManageBean)
                }
                loadDialog.dismiss()
                EventBus.getDefault().post(
                    TtsBean(
                        stringBuild(memberManageBean?.nickName, "充值", price.subPoint(), "元欢迎下次光临")
                    )
                )
                startActivity<WalletCompleteOrderActivity>(
                    CommonConstant.MEMBER_BEAN to memberManageBean,
                    "paySuccessBean" to it
                )
                onBackActivity()
            }
            requestResult.observe(this@WalletOrderActivity) {
                loadDialog.dismiss()
            }
            walletRechargeBean.observe(this@WalletOrderActivity) {
                when (cardType) {
                    0 -> {
                        rechargeActivityListBean.addAll(it.gasolineCard)
                        if (it.gasolineCard.isEmpty()) {
                            binding.choiceActivity.isEnabled = false
                            binding.activity.hint = "无充值活动"
                        } else {
                            binding.activity.hint = "选择充值活动"
                        }
                    }
                    1 -> {
                        rechargeActivityListBean.addAll(it.dieselOilCard)
                        if (it.dieselOilCard.isEmpty()) {
                            binding.choiceActivity.isEnabled = false
                            binding.activity.hint = "无充值活动"
                        } else {
                            binding.activity.hint = "选择充值活动"
                        }
                    }
                    2 -> {
                        rechargeActivityListBean.addAll(it.currency)
                        if (it.currency.isEmpty()) {
                            binding.choiceActivity.isEnabled = false
                            binding.activity.hint = "无充值活动"
                        } else {
                            binding.activity.hint = "选择充值活动"
                        }
                    }
                    else -> {
                        rechargeActivityListBean.addAll(it.naturalGasCard)
                        if (it.naturalGasCard.isEmpty()) {
                            binding.choiceActivity.isEnabled = false
                            binding.activity.hint = "无充值活动"
                        } else {
                            binding.activity.hint = "选择充值活动"
                        }
                    }
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CommonConstant.REQUEST_CODE) {
            val code = data?.getStringExtra(CommonConstant.SCAN_CODE)
            if (code != null) {
                loadDialog = showLoadingDialog("查询支付结果")
                loadDialog.show()
                model.play(code, orderNo, price.toDouble())
            }
        }
    }
}