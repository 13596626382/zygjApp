package com.tx.zygj.ui.wallet

import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.llx.common.util.getCompatDrawable
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.WalletRechargeMoneyBean
import com.tx.zygj.databinding.ItemWallerRechargeMoneyBinding

class WalletRechargeMoneyAdapter : BaseAdapter<WalletRechargeMoneyBean, ItemWallerRechargeMoneyBinding>(
    R.layout.item_waller_recharge_money,
    BR.walletRechargeMoneyBean
) {

    override fun convert1(
        binding: ItemWallerRechargeMoneyBinding,
        item: WalletRechargeMoneyBean,
        position: Int
    ) {
        binding.money.background =
            if (item.isCheck) getCompatDrawable(R.drawable.fillet_f38b26_5) else getCompatDrawable(R.drawable.fillet_707070_4)
        binding.money.setTextColor(
            if (item.isCheck) getCompatColor(R.color.color_F38B26) else getCompatColor(
                R.color.color_3D4255
            )
        )
    }
}