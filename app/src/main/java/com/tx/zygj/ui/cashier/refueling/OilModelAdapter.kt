package com.tx.zygj.ui.cashier.refueling

import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.llx.common.util.getCompatDrawable
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.OilBean
import com.tx.zygj.databinding.ItemModelOilsBinding

class OilModelAdapter : BaseAdapter<OilBean.OilModelBean, ItemModelOilsBinding>(R.layout.item_model_oils, BR.oliModelBean) {
    override fun convert1(binding: ItemModelOilsBinding, item: OilBean.OilModelBean, position: Int) {
        if (item.isCheck) {
            binding.oli.background = getCompatDrawable(R.drawable.change_fc664a_f38b26_4)
            binding.oli.setTextColor(getCompatColor(R.color.white))
        } else {
            binding.oli.background = getCompatDrawable(R.drawable.fillet_f5f5f6_4)
            binding.oli.setTextColor(getCompatColor(R.color.color_F38B26))
        }

    }
}