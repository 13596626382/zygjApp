package com.tx.zygj.ui.member.exchange

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.llx.common.base.BaseAdapter
import com.tx.zygj.R
import com.tx.zygj.databinding.ItemMemberExchangeRecordBinding
import com.tx.zygj.databinding.ViewMemberExchangeRecordBinding

class MemberExchangeRecordAdapter :
    BaseAdapter<String, ItemMemberExchangeRecordBinding>(R.layout.item_member_exchange_record, 0) {
    override fun convert1(binding: ItemMemberExchangeRecordBinding, item: String, position: Int) {
        for (i in 0..1) {
            val shopBinding = DataBindingUtil.inflate<ViewMemberExchangeRecordBinding>(
                LayoutInflater.from(context),
                R.layout.view_member_exchange_record,
                binding.layoutShop,
                false
            )
            binding.layoutShop.addView(shopBinding.root)
        }

    }
}