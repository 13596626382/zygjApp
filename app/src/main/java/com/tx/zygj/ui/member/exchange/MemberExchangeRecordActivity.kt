package com.tx.zygj.ui.member.exchange

import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityMemberExchangeRecordBinding

/**
 * 兑换记录
 */
class MemberExchangeRecordActivity :
    BaseActivity<ActivityMemberExchangeRecordBinding>(R.layout.activity_member_exchange_record) {

    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.recyclerView.bind(MemberExchangeRecordAdapter()) {
            setList(listOf("", "", "", ""))
        }
    }
}