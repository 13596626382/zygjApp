package com.tx.zygj.dialog.order.wallet

import android.view.View
import com.llx.common.base.BaseAdapter
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.WalletRechargeBean
import com.tx.zygj.databinding.ItemDialogWalletOrderBinding

class WalletOrderDialogAdapter :
    BaseAdapter<WalletRechargeBean.RechargeActivityBean, ItemDialogWalletOrderBinding>(
        R.layout.item_dialog_wallet_order,
        BR.rechargeActivityBean
    ) {
    var price = 0.00
    lateinit var onChoiceClickListener: (WalletRechargeBean.RechargeActivityBean) -> Unit
    override fun convert1(
        binding: ItemDialogWalletOrderBinding,
        item: WalletRechargeBean.RechargeActivityBean,
        position: Int
    ) {
        if (price < item.rechargeAmount) {
            binding.activity.visibility = View.VISIBLE
            binding.choice.visibility = View.GONE
        } else {
            binding.activity.visibility = View.GONE
            binding.choice.visibility = View.VISIBLE
        }
        binding.choice.setOnSingleClickListener {
            onChoiceClickListener.invoke(item)
        }
    }
}