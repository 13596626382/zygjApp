package com.tx.zygj.ui.cashier.shop

import android.view.View
import com.llx.common.base.BaseAdapter
import com.llx.common.util.toast
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean
import com.tx.zygj.databinding.ItemShopBinding

class ShopCashierAdapter : BaseAdapter<ShopBean, ItemShopBinding>(R.layout.item_shop, BR.shopBean) {
    lateinit var onQuantityChangeListener: (Boolean) -> Unit //数量变化，true 增加 false 减少
    override fun convert1(binding: ItemShopBinding, item: ShopBean, position: Int) {
        binding.addQuantity.setOnClickListener {
            data[position].quantity++
            binding.quantity.text = data[position].quantity.toString()
            binding.quantity.visibility = View.VISIBLE
            binding.reduceQuantity.visibility = View.VISIBLE
//            onQuantityChangeListener.invoke(true)
        }
        binding.reduceQuantity.setOnClickListener {
            if (data[position].quantity == 0) {
                context.toast("最少购买一件")
                return@setOnClickListener
            }
            data[position].quantity--
            binding.quantity.text = data[position].quantity.toString()
//            onQuantityChangeListener.invoke(false)
        }
    }
}