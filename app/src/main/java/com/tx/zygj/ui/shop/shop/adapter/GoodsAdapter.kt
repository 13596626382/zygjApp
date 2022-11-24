package com.tx.zygj.ui.shop.shop.adapter

import android.view.View
import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatDrawable
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsBean
import com.tx.zygj.databinding.ItemGoodsBinding

//商品
class GoodsAdapter : BaseAdapter<GoodsBean, ItemGoodsBinding>(R.layout.item_goods, BR.goodsBean) {
    lateinit var onChangeQuantityListener: (Boolean, GoodsBean) -> Unit
    override fun convert1(binding: ItemGoodsBinding, item: GoodsBean, position: Int) {
        if (item.quantity > 0) {
            binding.quantity.visibility = View.VISIBLE
            binding.reduceQuantity.visibility = View.VISIBLE
            binding.quantity.text = item.quantity.toString()
        } else {
            binding.quantity.visibility = View.INVISIBLE
            binding.reduceQuantity.visibility = View.INVISIBLE
        }

        if (item.stock == data[position].quantity) {
            binding.addQuantity.isEnabled = false
            binding.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy_no))
        } else {
            binding.addQuantity.isEnabled = true
            binding.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy))
        }
        
        binding.addQuantity.setOnSingleClickListener {
            data[position].quantity++
            binding.quantity.visibility = View.VISIBLE
            binding.reduceQuantity.visibility = View.VISIBLE
            binding.quantity.text = data[position].quantity.toString()
            onChangeQuantityListener.invoke(true, item)
            if (item.stock == data[position].quantity) {
                binding.addQuantity.isEnabled = false
                binding.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy_no))
            }
        }

        binding.reduceQuantity.setOnSingleClickListener {
            data[position].quantity--
            binding.quantity.text = data[position].quantity.toString()
            onChangeQuantityListener.invoke(false, item)
            if (!binding.addQuantity.isEnabled) {
                binding.addQuantity.isEnabled = true
                binding.addQuantity.setImageDrawable(getCompatDrawable(R.drawable.icon_quantiy))
            }
            if (data[position].quantity == 0) {
                binding.quantity.visibility = View.INVISIBLE
                binding.reduceQuantity.visibility = View.INVISIBLE
                return@setOnSingleClickListener
            }
        }
    }
}