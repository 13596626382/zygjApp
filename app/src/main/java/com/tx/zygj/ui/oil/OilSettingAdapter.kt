package com.tx.zygj.ui.oil

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.llx.common.base.BaseAdapter
import com.llx.common.util.addAfterTextChanged
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.OilBean
import com.tx.zygj.databinding.ItemOilSettingBinding
import com.tx.zygj.databinding.ViewOilBinding

class OilSettingAdapter :
    BaseAdapter<OilBean, ItemOilSettingBinding>(R.layout.item_oil_setting, BR.oilBean) {

    override fun convert1(binding: ItemOilSettingBinding, item: OilBean, position: Int) {
        item.oil.forEachIndexed { index, OilModelBean ->
            val viewOilBinding: ViewOilBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_oil, null, false)
            viewOilBinding.price.setText(OilModelBean.price.toString())
            viewOilBinding.type.text = OilModelBean.name
            viewOilBinding.price.addAfterTextChanged {
                data[position].oil[index].price = if (this == "") 0.00 else this.toDouble()
            }
            binding.linearLayout.addView(viewOilBinding.root)
        }

    }
}