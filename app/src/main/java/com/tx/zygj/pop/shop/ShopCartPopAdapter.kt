package com.tx.zygj.pop.shop

import android.view.View
import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.databinding.ItemPopShopBinding

class ShopCartPopAdapter :
    BaseAdapter<GoodsBean, ItemPopShopBinding>(R.layout.item_pop_shop, BR.goodsBean) {
    override fun convert1(binding: ItemPopShopBinding, item: GoodsBean, position: Int) {
        if (item.quantity > 0) {
            binding.quantity.visibility = View.VISIBLE
            binding.reduceQuantity.visibility = View.VISIBLE
            binding.quantity.text = item.quantity.toString()
        } else {
            binding.quantity.visibility = View.INVISIBLE
            binding.reduceQuantity.visibility = View.INVISIBLE
        }

    }
}