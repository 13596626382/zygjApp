package com.tx.zygj.ui.shop.fragment.adapter

import android.view.View
import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean
import com.tx.zygj.databinding.ItemShopBinding

class ShopAdapter : BaseAdapter<ShopBean, ItemShopBinding>(R.layout.item_shop, BR.shopBean) {
    lateinit var onQuantityChangeListener: (Boolean) -> Unit //数量变化，true 增加 false 减少
    override fun convert1(binding: ItemShopBinding, item: ShopBean, position: Int) {

        binding.addQuantity.setOnClickListener {
            data[position].quantity++
            binding.quantity.text = data[position].quantity.toString()
            binding.quantity.visibility = View.VISIBLE
            binding.reduceQuantity.visibility = View.VISIBLE
            onQuantityChangeListener.invoke(true)
        }
        binding.reduceQuantity.setOnClickListener {
            data[position].quantity--
            binding.quantity.text = data[position].quantity.toString()
            if (data[position].quantity == 0) {
                binding.quantity.visibility = View.GONE
                binding.reduceQuantity.visibility = View.GONE
            }
            onQuantityChangeListener.invoke(false)
        }
    }
}