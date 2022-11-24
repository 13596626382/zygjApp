package com.tx.zygj.ui.cashier.goods

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.databinding.ActivityGoodsCashierBinding
import com.tx.zygj.ui.scan.ScanCodeActivity

/**
 * 购买商品
 */
class GoodsCashierActivity :
    BaseActivity<ActivityGoodsCashierBinding>(R.layout.activity_goods_cashier) {
    private val model: GoodsCashierViewModel by viewModels()
    private lateinit var loadDialog: LoadingPopupView
    private val mAdapter by lazy { GoodsCashierAdapter() }
    private lateinit var popUpView: LoadingPopupView
    private var goodsBean: MutableList<GoodsBean> = arrayListOf()
    private var goodsJson: ArrayList<Map<String, Any>> = arrayListOf() //存储商品id与数量
    private var price = 0.00
    private var payMode = ""
    private var orderNo = ""
    private lateinit var memberBean: MemberManageBean
    override fun initData() {
        binding.titleBar.setOnBack(this)
        popUpView = showLoadingDialog("加载中")
        memberBean = intentParcelableExtras("memberBean")!!
        goodsBean = intentParcelableArrayListExtras("goodsBean")!!
        goodsBean.forEach {
            price += it.quantity * it.sellingPrice!!
            goodsJson.add(mapOf<String, Any>("id" to it.id!!, "number" to it.quantity))
        }

        binding.memberBean = memberBean
        binding.integral.text = stringBuild("支付金额: ", price)
        binding.recyclerView.bind(mAdapter) {
            setList(goodsBean)
        }
        binding.settle.setOnSingleClickListener {
            popUpView.show()
            if (payMode == "") {
                toast("请选择支付方式")
                return@setOnSingleClickListener
            }
            model.getGoodsOrderNo(payMode, memberBean.id, price.toString(), goodsJson.toJson())
        }

        if (memberBean.purse!!.toDouble() > price) {
            binding.purseBalance.text = stringBuild("余额", memberBean.purse, "元")
            binding.purseImage.background = getCompatDrawable(R.drawable.icon_payment)
            payMode = "通用钱包支付"
        } else {
            binding.purseBalance.text = "余额不足"
            binding.purseImage.visibility = View.INVISIBLE
            binding.purseLayout.isEnabled = false
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
        binding.smallChangeLayout.setOnSingleClickListener {
            resetLayout()
            binding.smallChangeImage.background = getCompatDrawable(R.drawable.icon_payment)
            payMode = "现金支付"
        }

        model.requestResult.observe(this) {
            popUpView.dismiss()
            if (it) {
                CommonConstant.isPaySuccess = true
                showConfirmDialog(content = "支付成功", block = {
                    onBackActivity()
                }, isHideCancel = true)
            }
        }

        model.goodsOrderNo.observe(this) {
            orderNo = it
            when (payMode) {
                "通用钱包支付" -> model.goodsCurrencyPayment(it, goodsJson.toJson())

                "扫码支付" -> {
                    startActivityForResult(
                        Intent(mContext, ScanCodeActivity::class.java).putExtra(
                            CommonConstant.SCAN_TYPE, 0
                        ), CommonConstant.REQUEST_CODE
                    )
                }
                else -> model.goodsCashPayment(it, goodsJson.toJson())
            }
        }
    }

    private fun resetLayout() {
        binding.scanImage.background = getCompatDrawable(R.drawable.icon_payment_1)
        binding.purseImage.background = getCompatDrawable(R.drawable.icon_payment_1)
        binding.smallChangeImage.background = getCompatDrawable(R.drawable.icon_payment_1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CommonConstant.REQUEST_CODE) {
            val code = data?.getStringExtra(CommonConstant.SCAN_CODE)
            if (code != null) {
                loadDialog = showLoadingDialog("查询支付结果")
                loadDialog.show()
                model.goodsPay(code, orderNo, price, goodsJson.toJson())
            } else {
                loadDialog.dismiss()
                toast("取消支付")
            }
        }
    }
}