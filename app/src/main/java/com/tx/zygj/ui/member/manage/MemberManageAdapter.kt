package com.tx.zygj.ui.member.manage

import com.llx.common.base.BaseAdapter1
import com.tx.zygj.BR
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.databinding.ItemMenberManageBinding

class MemberManageAdapter :
    BaseAdapter1<MemberManageBean, ItemMenberManageBinding>(
        R.layout.item_menber_manage,
        BR.memberManageBean
    )