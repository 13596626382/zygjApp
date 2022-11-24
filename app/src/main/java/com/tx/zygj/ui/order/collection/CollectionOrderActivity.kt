package com.tx.zygj.ui.order.collection

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.*
import com.tx.zygj.databinding.ActivityOrderBinding
import com.tx.zygj.ui.complete.collection.CompleteOrderActivity
import com.tx.zygj.ui.scan.ScanCodeActivity
import com.tx.zygj.util.SunmiPrintHelper
import org.greenrobot.eventbus.EventBus

//收银订单页
class CollectionOrderActivity : BaseActivity<ActivityOrderBinding>(R.layout.activity_order) {

    private val model: CollectionOrderViewModel by viewModels()
    private lateinit var loadDialog: LoadingPopupView

    private var memberManageBean: MemberManageBean? = null
    private var oilModelBean: OilBean.OilModelBean? = null //油品
    private var oilGunBean: OilGunBean? = null //油枪
    private var oilerBean: OilerBean? = null //加油员
    private var collectionDiscountBean: CollectionDiscountBean? = null//优惠活动

    private var price = "" //支付金额
    private var orderNo = "" //订单号
    private var oilsRise = "" //油数量

    private var payMode = "" //支付方式
    override fun initData() {
        binding.titleBarView.setOnBack(this)
        loadDialog = showLoadingDialog("生成订单中")
        memberManageBean = intentParcelableExtras(CommonConstant.MEMBER_BEAN)
        oilerBean = intentParcelableExtras("oilerBean")
        price = intentStringExtras("price")!!
        binding.memberBeam = memberManageBean

        binding.ordinaryLayout.visibility = View.VISIBLE
        oilModelBean = intentParcelableExtras("oilModelBean")
        oilGunBean = intentParcelableExtras("oilGunBean")
        oilsRise = intentStringExtras("oilsRise")!!
        binding.oilModelBean = oilModelBean
        binding.price.text = price
        binding.actualPrice.text = price
        binding.operator.text = oilerBean?.name
        binding.oilsRise.text = oilsRise

        if (memberManageBean != null) {

            when (oilModelBean?.typeId) {
                1 -> {
                    binding.oilCardName.text = "汽油卡支付"
                    if (memberManageBean?.gasolineCard!!.toDouble() > price.toDouble()) {
                        binding.oilBalance.text =
                            stringBuild("余额", memberManageBean?.gasolineCard, "元")
                    } else {
                        binding.oilBalance.text = "余额不足"
                        binding.oilCardImage.visibility = View.GONE
                        binding.oilCardLayout.isEnabled = false
                    }
                }
                2 -> {
                    binding.oilCardName.text = "柴油卡支付"
                    if (memberManageBean?.dieselCard!!.toDouble() > price.toDouble()) {
                        binding.oilBalance.text =
                            stringBuild("余额", memberManageBean?.dieselCard, "元")
                    } else {
                        binding.oilBalance.text = "余额不足"
                        binding.oilCardImage.visibility = View.GONE
                        binding.oilCardLayout.isEnabled = false
                    }
                }
                else -> {
                    binding.oilCardName.text = "天然气卡支付"
                    if (memberManageBean?.naturalGas!!.toDouble() > price.toDouble()) {
                        binding.oilBalance.text =
                            stringBuild("余额", memberManageBean?.naturalGas, "元")
                    } else {
                        binding.oilBalance.text = "余额不足"
                        binding.oilCardImage.visibility = View.GONE
                        binding.oilCardLayout.isEnabled = false
                    }
                }
            }
            //通用钱包
            if (memberManageBean?.purse!!.toDouble() > price.toDouble()) {
                binding.purseBalance.text = stringBuild("余额", memberManageBean?.purse, "元")
            } else {
                binding.purseBalance.text = "余额不足"
                binding.purseImage.visibility = View.GONE
                binding.purseLayout.isEnabled = false
            }
        }

        binding.settle.setOnSingleClickListener {
            if (payMode == "") {
                toast("请选择支付方式")
            } else {
                loadDialog.show()
                if (orderNo != "") {
                    model.requestOrderNo.value = orderNo
                } else {
                    model.generateOrder(
                        oilModelBean?.model,
                        oilModelBean?.typeId,
                        oilerBean?.name,
                        collectionDiscountBean?.actual ?: price.toDouble(),
                        price.toDouble(),
                        memberManageBean?.phone,
                        payMode,
                        oilsRise.substring(1, oilsRise.length - 1).toDouble(),
                        oilModelBean?.price,
                        oilGunBean?.oilGunNumber,
                        collectionDiscountBean?.integral ?: 0,
                        collectionDiscountBean?.discount ?: 0.00,
                        collectionDiscountBean?.preferentialMethod ?: "无"
                    )
                }
            }
        }

        binding.oilCardLayout.setOnSingleClickListener {
            resetLayout()
            binding.oilCardImage.background = getCompatDrawable(R.drawable.icon_payment)
            payMode = "加油卡支付"
        }

        binding.purseLayout.setOnSingleClickListener {
            resetLayout()
            binding.purseImage.background = getCompatDrawable(R.drawable.icon_payment)
            payMode = "通用钱包支付"
        }

        binding.scanLayout.setOnSingleClickListener {
            resetLayout()
            binding.scanImage.background = getCompatDrawable(R.drawable.icon_payment)
            payMode = "扫码支付"
        }

        model.apply {
            if (memberManageBean != null) {
                getRefuelingDiscount(price.toDouble(), oilModelBean?.id, memberManageBean?.id)
            }
            requestOrderNo.observe(this@CollectionOrderActivity) {
                loadDialog.dismiss()
                orderNo = it
                when (payMode) {
                    "扫码支付" -> {
                        startActivityForResult(
                            Intent(mContext, ScanCodeActivity::class.java).putExtra(
                                CommonConstant.SCAN_TYPE, 0
                            ), CommonConstant.REQUEST_CODE
                        )
                    }
                    "通用钱包支付" -> {
                        model.cardPayment(
                            orderNo,
                            memberManageBean?.phone,
                            this@CollectionOrderActivity.collectionDiscountBean?.actual
                                ?: price.toDouble(),
                            this@CollectionOrderActivity.collectionDiscountBean?.integral
                                ?: 0,
                            oilerBean?.id
                        )
                    }
                    else -> {
                        model.oidCardPayment(
                            orderNo,
                            memberManageBean?.phone,
                            this@CollectionOrderActivity.collectionDiscountBean?.actual
                                ?: price.toDouble(),
                            this@CollectionOrderActivity.collectionDiscountBean?.integral
                                ?: 0,
                            oilModelBean?.typeId,
                            oilerBean?.id
                        )
                    }
                }
            }
            paySuccessBean.observe(this@CollectionOrderActivity) {
                if (binding.tickets.isChecked) {
                    SunmiPrintHelper.sendCashierRawData(it, memberManageBean)
                }
                loadDialog.dismiss()
                EventBus.getDefault().post(
                    TtsBean(
                        stringBuild(it.memberName, "支付", it.actual?.subPoint(), "元, 欢迎下次光临"),
                        it.orderNo
                    )
                )
                startActivity<CompleteOrderActivity>(
                    CommonConstant.MEMBER_BEAN to memberManageBean,
                    "paySuccessBean" to it
                )
                onBackActivity()
            }
            requestResult.observe(this@CollectionOrderActivity) {
                loadDialog.dismiss()
            }
            collectionDiscountBean.observe(this@CollectionOrderActivity) {
                binding.discountPrice.text = it.discount.toString()
                binding.integral.text = it.integral.toString()
                binding.actualPrice.text = it.actual.toString()
                this@CollectionOrderActivity.collectionDiscountBean = it
            }
        }
    }

    private fun resetLayout() {
        binding.oilCardImage.background = getCompatDrawable(R.drawable.icon_payment_1)
        binding.purseImage.background = getCompatDrawable(R.drawable.icon_payment_1)
        binding.scanImage.background = getCompatDrawable(R.drawable.icon_payment_1)
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