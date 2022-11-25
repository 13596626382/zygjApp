package com.tx.zygj.ui.exchange.gift

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.databinding.ActivityGiftExchangeBinding
import com.tx.zygj.util.SunmiPrintHelper

/**
 * 积分兑换
 */
class GiftExchangeActivity :
    BaseActivity<ActivityGiftExchangeBinding>(R.layout.activity_gift_exchange) {
    private val model: GiftExchangeViewModel by viewModels()
    private val mAdapter by lazy { GiftExchangeAdapter() }
    private lateinit var popUpView: LoadingPopupView
    private var goodsBean: MutableList<GoodsBean> = arrayListOf()
    private var goodsJson: ArrayList<Map<String, Any>> = arrayListOf() //存储商品id与数量
    private var integral = 0
    private lateinit var memberBean: MemberManageBean
    override fun initData() {
        binding.titleBar.setOnBack(this)

        popUpView = showLoadingDialog("加载中")
        memberBean = intentParcelableExtras("memberBean")!!
        goodsBean = intentParcelableArrayListExtras("goodsBean")!!
        goodsBean.forEach {
            integral += it.quantity * it.bonusPoints!!
            goodsJson.add(mapOf<String, Any>("id" to it.id!!, "number" to it.quantity))
        }

        binding.memberBean = memberBean
        binding.integral.text = stringBuild("扣除积分: ", integral)
        if (integral > memberBean.integral!!.toInt()) {
            binding.settle.text = "积分不足"
            binding.settle.background = getCompatDrawable(R.drawable.fillet_f5f5f6_4)
            binding.settle.isEnabled = false
        }
        binding.recyclerView.bind(mAdapter) {
            setList(goodsBean)
        }
        binding.settle.setOnSingleClickListener {
            popUpView.show()
            model.getGoodsOrderNo(memberBean.id, integral.toString(), goodsJson.toJson())
        }
        model.requestResult.observe(this) {
            popUpView.dismiss()
            if (it) {
                CommonConstant.isPaySuccess = true
                SunmiPrintHelper.sendGoodsRawData(goodsBean, false)
                showConfirmDialog(content = "兑换成功", block = {
                    onBackActivity()
                }, isHideCancel = true)
            }
        }
    }
}