package com.tx.zygj.ui.shop.shop.adapter

import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.ShopBean
import com.tx.zygj.databinding.ItemClassifyBinding

//分类
class ClassifyAdapter : BaseAdapter<ShopBean.ShopClassifyBean, ItemClassifyBinding>(
    R.layout.item_classify,
    BR.goodsClassifyBean
) {
    var isFirst = false
    override fun convert1(binding: ItemClassifyBinding, item: ShopBean.ShopClassifyBean, position: Int) {
        if (!isFirst && position == 0) {
            data[position].isCheck = true
            isFirst = true
        }
        binding.shopClassify.setBackgroundColor(
            if (item.isCheck) getCompatColor(R.color.color_F5F5F6) else getCompatColor(
                R.color.white
            )
        )
        binding.shopClassify.setTextColor(
            if (item.isCheck) getCompatColor(R.color.color_F38B26) else getCompatColor(
                R.color.color_939393
            )
        )
    }
}