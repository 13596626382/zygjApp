package com.tx.zygj.ui.shop.fragment.integral

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.llx.common.CommonConstant
import com.llx.common.base.BaseFragment
import com.llx.common.util.showPopupView
import com.llx.common.util.startActivity
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean
import com.tx.zygj.bean.ShopSortBean
import com.tx.zygj.databinding.FragmentShopIntegralBinding
import com.tx.zygj.pop.shop.ShopPopupView
import com.tx.zygj.ui.cashier.shop.ShopCashierActivity
import com.tx.zygj.ui.shop.fragment.adapter.ShopAdapter
import com.tx.zygj.ui.shop.fragment.adapter.ShopSortAdapter

/**
 * 积分兑换
 */
class IntegralFragment :
    BaseFragment<FragmentShopIntegralBinding>(R.layout.fragment_shop_integral) {
    private val mSortAdapter by lazy { ShopSortAdapter() }
    private val mShopAdapter by lazy { ShopAdapter() }
    private var shopQuantity = 0
    override fun initData() {
        binding.sortRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mSortAdapter
        }
        binding.shopRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mShopAdapter
        }
        val list = ArrayList<ShopSortBean>()
        val shopList = ArrayList<ShopBean>()
        for (i in 0..10) {
            list.add(ShopSortBean("商品$i", i == 0))
            shopList.add(ShopBean("XXX商品$i", 0))
        }
        mSortAdapter.setList(list)
        mShopAdapter.setList(shopList)
        mShopAdapter.onQuantityChangeListener = {
            shopQuantity = if (it) shopQuantity + 1 else shopQuantity - 1
            if (shopQuantity < 1) {
                binding.shopCount.visibility = View.GONE
            } else {
                binding.shopCount.visibility = View.VISIBLE
                binding.shopCount.text = shopQuantity.toString()
            }

        }
        binding.shop.setOnClickListener {
            mContext.showPopupView(binding.linearLayout6, ShopPopupView(mContext))
        }
        binding.settle.setOnClickListener {
            startActivity<ShopCashierActivity>(CommonConstant.INTENT_TITLE to "积分兑换")
        }
    }
}