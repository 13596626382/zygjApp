package com.tx.zygj.ui.shop.manage.adapter

import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.GoodsClassifyBean
import com.tx.zygj.databinding.ItemClassifyManageBinding

//分类
class ClassifyManageAdapter : BaseAdapter<GoodsClassifyBean, ItemClassifyManageBinding>(
    R.layout.item_classify_manage,
    BR.goodsClassifyBean
) {
    var isFirst = false
    override fun convert1(binding: ItemClassifyManageBinding, item: GoodsClassifyBean, position: Int) {
        if (!isFirst && position == 0) {
            data[position].isCheck = true
            isFirst = true
        }
        binding.shopSort.setBackgroundColor(
            if (item.isCheck) getCompatColor(R.color.color_F5F5F6) else getCompatColor(
                R.color.white
            )
        )
        binding.shopSort.setTextColor(
            if (item.isCheck) getCompatColor(R.color.color_F38B26) else getCompatColor(
                R.color.color_939393
            )
        )
    }
}