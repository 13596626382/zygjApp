package com.tx.zygj.ui.order.wallet

import android.view.View
import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.WalletRechargeBean
import com.tx.zygj.databinding.ItemRechargeActivityBinding

class WalletOrderAdapter :
    BaseAdapter<WalletRechargeBean.RechargeActivityBean, ItemRechargeActivityBinding>(
        R.layout.item_recharge_activity,
        BR.rechargeActivityBean
    ) {
    var price = 0.00
    override fun convert1(
        binding: ItemRechargeActivityBinding,
        item: WalletRechargeBean.RechargeActivityBean,
        position: Int
    ) {
        if (price < item.rechargeAmount) {
            binding.activity.visibility = View.VISIBLE
            binding.checkbox.visibility = View.GONE
        } else {
            binding.activity.visibility = View.GONE
            binding.checkbox.visibility = View.VISIBLE
        }
    }
}