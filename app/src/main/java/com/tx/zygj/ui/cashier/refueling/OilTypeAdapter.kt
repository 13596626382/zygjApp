package com.tx.zygj.ui.cashier.refueling

import android.view.View
import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.OilBean
import com.tx.zygj.databinding.ItemTypeOilsBinding

class OilTypeAdapter : BaseAdapter<OilBean, ItemTypeOilsBinding>(R.layout.item_type_oils, BR.oilBean) {
    private var isFirst = true
    override fun convert1(binding: ItemTypeOilsBinding, item: OilBean, position: Int) {

        if (item.isCheck) {
            binding.oilView.visibility = View.VISIBLE
            binding.oilText.setTextColor(getCompatColor(R.color.color_F38B26))
        } else {
            binding.oilView.visibility = View.GONE
            binding.oilText.setTextColor(getCompatColor(R.color.color_3D4255))
        }
        if (isFirst && position == 0) {
            binding.oilView.visibility = View.VISIBLE
            binding.oilText.setTextColor(getCompatColor(R.color.color_F38B26))
            isFirst = false
        }
    }
}