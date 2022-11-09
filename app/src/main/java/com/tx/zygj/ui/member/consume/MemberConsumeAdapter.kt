package com.tx.zygj.ui.member.consume

import com.llx.common.base.BaseAdapter
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.ConsumeBean
import com.tx.zygj.databinding.ItemMemberConsumeRecordBinding

class MemberConsumeAdapter : BaseAdapter<ConsumeBean, ItemMemberConsumeRecordBinding>(
    R.layout.item_member_consume_record,
    BR.consumeBean
)