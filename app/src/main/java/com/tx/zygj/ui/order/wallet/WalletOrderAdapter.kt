package com.tx.zygj.ui.order.wallet

import android.view.View
import com.llx.common.base.BaseAdapter
import com.llx.common.util.getCompatDrawable
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.PaymentMethodBean
import com.tx.zygj.databinding.ItemPaymentBinding

class WalletOrderAdapter : BaseAdapter<PaymentMethodBean, ItemPaymentBinding>(
    R.layout.item_payment,
    BR.paymentMethodBean
) {
    var price = ""

    override fun convert1(binding: ItemPaymentBinding, item: PaymentMethodBean, position: Int) {
//
//            if (item.isCheck)  else
        if (item.isCheck) {
            binding.image.background = getCompatDrawable(R.drawable.icon_payment)
        } else {
            binding.image.background = getCompatDrawable(R.drawable.icon_payment_1)
        }

        if (item.type != "扫码支付" && item.balance!!.toDouble() < price.toDouble()) {
            binding.balance.text = "余额不足"
            binding.image.visibility = View.GONE
            binding.root.isEnabled = false
        }
    }
}