package com.tx.zygj.ui.wallet

import android.view.View
import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatColor
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.WalletRechargeTypeBean
import com.tx.zygj.databinding.ItemWallerRechargeTypeBinding

class WalletRechargeTypeAdapter :
    BaseAdapter<WalletRechargeTypeBean, ItemWallerRechargeTypeBinding>(
        R.layout.item_waller_recharge_type,
        BR.walletRechargeTypeBean
    ) {

    override fun convert1(
        binding: ItemWallerRechargeTypeBinding,
        item: WalletRechargeTypeBean,
        position: Int
    ) {
        binding.type.setTextColor(
            if (item.isCheck) getCompatColor(R.color.color_F38B26) else getCompatColor(
                R.color.color_939393
            )
        )
        binding.typeLine.visibility = (
                if (item.isCheck) View.VISIBLE else View.GONE
                )
    }
}