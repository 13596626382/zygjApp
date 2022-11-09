package com.tx.zygj.ui.cashier.refueling

import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.llx.common.util.getCompatDrawable
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.OilGunBean
import com.tx.zygj.databinding.ItemOilGunBinding

class OilGunAdapter : BaseAdapter<OilGunBean, ItemOilGunBinding>(R.layout.item_oil_gun, BR.oilGunBean) {

    override fun convert1(binding: ItemOilGunBinding, item: OilGunBean, position: Int) {

        if (item.isCheck) {
            binding.oli.background = getCompatDrawable(R.drawable.change_fc664a_f38b26_4)
            binding.oli.setTextColor(getCompatColor(R.color.white))
        } else {
            binding.oli.background = getCompatDrawable(R.drawable.fillet_f5f5f6_4)
            binding.oli.setTextColor(getCompatColor(R.color.color_F38B26))
        }
    }
}