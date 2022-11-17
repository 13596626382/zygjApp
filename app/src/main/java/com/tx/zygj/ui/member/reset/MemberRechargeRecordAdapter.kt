package com.tx.zygj.ui.member.reset

import com.llx.common.base.BaseAdapter
import com.llx.common.util.showConfirmDialog
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.MemberRechargeRecordBean
import com.tx.zygj.databinding.ItemMemberRechargeRecordBinding


class MemberRechargeRecordAdapter :
    BaseAdapter<MemberRechargeRecordBean, ItemMemberRechargeRecordBinding>(
        R.layout.item_member_recharge_record,
        BR.memberRechargeRecordBean
    ) {
    var onDeleteClickListener: ((MemberRechargeRecordBean, Int) -> Unit)? = null
    override fun convert1(
        binding: ItemMemberRechargeRecordBinding,
        item: MemberRechargeRecordBean,
        position: Int
    ) {
        binding.delete.showConfirmDialog(content = "删除该条记录") {
            onDeleteClickListener?.invoke(item, position)
        }
    }
}