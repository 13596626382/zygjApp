package com.tx.zygj.ui.shop.fragment.adapter

import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.ShopSortBean
import com.tx.zygj.databinding.ItemShopSortBinding

class ShopSortAdapter : BaseAdapter<ShopSortBean, ItemShopSortBinding>(R.layout.item_shop_sort, BR.shopSortBean) {
    override fun convert1(binding: ItemShopSortBinding, item: ShopSortBean, position: Int) {
        binding.shopSort.setBackgroundColor(if (item.isCheck) getCompatColor(R.color.color_F5F5F6) else getCompatColor(R.color.white))
        binding.shopSort.setTextColor(if (item.isCheck) getCompatColor(R.color.color_F38B26) else getCompatColor(R.color.color_939393))
        binding.shopSort.setOnSingleClickListener {
            if (data[position].isCheck) return@setOnSingleClickListener
            data.forEach {
                it.isCheck = false
            }
            data[position].isCheck = true
            notifyDataSetChanged()
        }
    }
}