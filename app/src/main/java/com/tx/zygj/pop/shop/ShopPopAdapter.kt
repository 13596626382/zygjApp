package com.tx.zygj.pop.shop

import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean
import com.tx.zygj.databinding.ItemPopShopBinding

class ShopPopAdapter : BaseAdapter<ShopBean, ItemPopShopBinding>(R.layout.item_pop_shop, BR.shopBean) {
    override fun convert1(binding: ItemPopShopBinding, item: ShopBean, position: Int) {
        binding.addQuantity.setOnClickListener {
            data[position].quantity++
            binding.quantity.text = data[position].quantity.toString()
        }

        binding.reduceQuantity.setOnClickListener {
            data[position].quantity--
            if (data[position].quantity == 0) {
                removeAt(position)
                return@setOnClickListener
            }
            binding.quantity.text = data[position].quantity.toString()
        }
    }
}