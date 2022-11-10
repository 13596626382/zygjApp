package com.tx.zygj.dialog.order.wallet

import com.llx.common.base.BaseDialogFragment
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.WalletRechargeBean
import com.tx.zygj.databinding.DialogWalletOrderBinding

class WalletOrderDialogFragment :
    BaseDialogFragment<DialogWalletOrderBinding>(R.layout.dialog_wallet_order) {
    private val mAdapter by lazy { WalletOrderDialogAdapter() }
    lateinit var onActivityClickListener: (WalletRechargeBean.RechargeActivityBean) -> Unit
    override fun init() {
        binding.recyclerView.bind(mAdapter) {
            setList(argumentsParcelableArrayList("rechargeActivityBean"))
            price = argumentString("price")!!.toDouble()
            onChoiceClickListener = {
                if (price >= it.rechargeAmount) {
                    onActivityClickListener.invoke(it)
                    dismiss()
                }
            }
        }
        binding.close.setOnSingleClickListener {
            dismiss()
        }
    }

    override fun getCanceled() = false

    override fun getDialogHeight() = 300.dp.toInt()
}