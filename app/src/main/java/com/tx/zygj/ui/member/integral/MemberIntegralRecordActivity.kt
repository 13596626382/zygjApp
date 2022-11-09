package com.tx.zygj.ui.member.integral

import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.llx.common.util.showDateDialog
import com.llx.common.util.toYMDateString
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityMemberIntegralRecordBinding

/**
 * 积分记录
 */
class MemberIntegralRecordActivity :
    BaseActivity<ActivityMemberIntegralRecordBinding>(R.layout.activity_member_integral_record) {


    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.recyclerView.bind(MemberIntegralRecordAdapter()) {
        }.setList(listOf("", "", "", "", ""))
        binding.selectDate.showDateDialog(true) {
            binding.selectDate.text = it.toYMDateString()
        }
    }
}