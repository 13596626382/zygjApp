package com.tx.zygj.pop.shop

import android.view.View
import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatDrawable
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.databinding.ItemPopShopBinding

class ShopCartPopAdapter :
    BaseAdapter<GoodsBean, ItemPopShopBinding>(R.layout.item_pop_shop, BR.goodsBean) {
    lateinit var onChangeQuantityListener: (MutableList<GoodsBean>) -> Unit
    override fun convert1(binding: ItemPopShopBinding, item: GoodsBean, position: Int) {
        if (item.quantity > 0) {
            binding.quantity.visibility = View.VISIBLE
            binding.reduceQuantity.visibility = View.VISIBLE
            binding.quantity.text = item.quantity.toString()
        } else {
            binding.quantity.visibility = View.INVISIBLE
            binding.reduceQuantity.visibility = View.INVISIBLE
        }

        binding.addQuantity.setOnSingleClickListener {
            data[position].quantity++
            binding.quantity.visibility = View.VISIBLE
            binding.reduceQuantity.visibility = View.VISIBLE
            binding.quantity.text = data[position].quantity.toString()
            if (item.stock == data[position].quantity) {
                binding.addQuantity.isEnabled = false
                binding.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy_no))
            }
            onChangeQuantityListener.invoke(data)
        }

        binding.reduceQuantity.setOnSingleClickListener {
            data[position].quantity--
            binding.quantity.text = data[position].quantity.toString()
            if (!binding.addQuantity.isEnabled) {
                binding.addQuantity.isEnabled = true
                binding.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy))
            }
            if (data[position].quantity == 0) {
                removeAt(position)
            }
            onChangeQuantityListener.invoke(data)
        }
    }
}