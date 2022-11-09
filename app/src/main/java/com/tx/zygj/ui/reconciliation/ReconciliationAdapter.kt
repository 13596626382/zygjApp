package com.tx.zygj.ui.reconciliation

import android.view.View
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.llx.common.base.BaseAdapter
import com.llx.common.util.stringBuild
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.ReconciliationBean
import com.tx.zygj.databinding.ItemReconciliationBinding

class ReconciliationAdapter : BaseAdapter<ReconciliationBean, ItemReconciliationBinding>(
    R.layout.item_reconciliation,
    BR.reconciliationBean
) {
    override fun convert2(
        holder: BaseDataBindingHolder<ItemReconciliationBinding>,
        binding: ItemReconciliationBinding,
        item: ReconciliationBean,
        position: Int
    ) {
        holder.setIsRecyclable(false)
        if (item.typeId == "消费") {
            if (item.memberPhone == null) {
                binding.type.text = "快速收银"
                binding.gasName.visibility = View.GONE
                binding.model.visibility = View.GONE
                binding.phone.visibility = View.GONE
            } else {
                binding.gasName.text = item.gasMan
                if (item.gunNumber != null) {
                    binding.type.text = stringBuild(item.gunNumber, "号枪")
                }
                binding.model.text = stringBuild(item.model, "#")
            }
        } else {
            binding.type.text = "钱包充值"
            binding.model.text = when (item.cardType) {
                "0" -> "汽油卡"
                "1" -> "柴油卡"
                "2" -> "通用钱包"
                else -> "天然气卡"
            }
            if (item.gasMan == null) {
                binding.gasName.visibility = View.GONE
            } else {
                binding.gasName.text = item.gasMan
            }
        }
        binding.money.text = stringBuild("￥", item.money)
    }

}