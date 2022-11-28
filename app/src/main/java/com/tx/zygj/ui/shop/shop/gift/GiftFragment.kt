package com.tx.zygj.ui.shop.shop.gift

import android.view.View
import androidx.fragment.app.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseFragment
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.databinding.FragmentGiftBinding
import com.tx.zygj.pop.shop.ShopCartPopupView
import com.tx.zygj.ui.exchange.gift.GiftExchangeActivity
import com.tx.zygj.ui.shop.shop.ShopActivity
import com.tx.zygj.ui.shop.shop.adapter.ClassifyAdapter
import com.tx.zygj.ui.shop.shop.adapter.GoodsAdapter

//礼品
class GiftFragment :
    BaseFragment<FragmentGiftBinding>(R.layout.fragment_gift) {
    private val mClassifyAdapter by lazy { ClassifyAdapter() }
    private val mGoodsAdapter by lazy { GoodsAdapter() }
    private val model: GiftViewModel by viewModels()
    private lateinit var shopCartPopupView: ShopCartPopupView

    private var goodsBean: MutableList<GoodsBean> = arrayListOf()
    private var shopQuantity = 0
    override fun initData() {
        binding.classifyRecyclerView.bind(mClassifyAdapter) {
            setOnItemClickListener { _, _, position ->
                if (data[position].isCheck) return@setOnItemClickListener
                data.forEach {
                    it.isCheck = false
                }
                data[position].isCheck = true
                notifyDataSetChanged()
                mGoodsAdapter.setList(data[position].goodsList)
            }
        }
        binding.goodsRecyclerView.bind(mGoodsAdapter) {
            setEmptyView(R.layout.view_empty)
            onChangeQuantityListener = { addOrReduce, bean ->
                val integral = binding.integral.textString().toInt()
                if (addOrReduce) {
                    shopQuantity += 1
                    binding.integral.text = (integral + bean.bonusPoints!!).toString()
                    if (goodsBean.contains(bean)) {
                        goodsBean[goodsBean.indexOf(bean)].quantity = bean.quantity
                    } else {
                        goodsBean.add(bean)
                    }
                } else {
                    shopQuantity -= 1
                    binding.integral.text = (integral - bean.bonusPoints!!).toString()
                    if (bean.quantity == 0) {
                        goodsBean.remove(bean)
                    } else {
                        goodsBean[goodsBean.indexOf(bean)].quantity = bean.quantity
                    }
                }
                if (shopQuantity < 1) {
                    binding.shopCount.visibility = View.GONE
                } else {
                    binding.shopCount.visibility = View.VISIBLE
                    binding.shopCount.text = shopQuantity.toString()
                }
            }
        }
        model.apply {
            getGift()
            shopGiftBean.observe(this@GiftFragment) {
                mClassifyAdapter.setList(it)
                mGoodsAdapter.setList(it[0].goodsList)
            }
        }

        binding.shop.setOnSingleClickListener {
            if (goodsBean.isEmpty()) return@setOnSingleClickListener
            shopCartPopupView = ShopCartPopupView(mContext, goodsBean)
            shopCartPopupView.onChangeQuantityListener = {
                goodsBean = it
                shopQuantity = 0
                var integral = 0

                goodsBean.forEach { it1 ->
                    shopQuantity += it1.quantity
                    integral += it1.quantity * it1.bonusPoints!!
                }
                if (shopQuantity < 1) {
                    binding.shopCount.visibility = View.GONE
                }
                binding.shopCount.text = shopQuantity.toString()
                binding.integral.text = integral.toString()
                mGoodsAdapter.notifyDataSetChanged()
            }
            mContext.showPopupView(binding.linearLayout6, shopCartPopupView)
        }


        binding.settle.setOnClickListener {
            val memberManageBean = (requireActivity() as ShopActivity).memberManageBean
            if (goodsBean.isEmpty()) {
                toast("请选择商品")
                return@setOnClickListener
            }
            if ((requireActivity() as ShopActivity).memberManageBean == null) {
                toast("请选择会员")
                return@setOnClickListener
            }
            startActivity<GiftExchangeActivity>(
                "goodsBean" to goodsBean,
                "memberBean" to memberManageBean
            )
        }
    }

    override fun update() {
        if (CommonConstant.isPaySuccess) {
            CommonConstant.isPaySuccess = false
            model.getGift()
            goodsBean.clear()
            shopQuantity = 0
            binding.shopCount.text = "0"
            binding.shopCount.visibility = View.INVISIBLE
            binding.integral.text = "0"
            (requireActivity() as ShopActivity).clearMember()
        }
    }
}