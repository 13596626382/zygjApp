package com.tx.zygj.ui.member.reset


import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.llx.common.util.intentStringExtras
import com.llx.common.util.startActivity
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityMemberRechargeRecordBinding
import com.tx.zygj.ui.record.RecordDetailsActivity

/**
 * 充值记录
 */
class MemberRechargeRecordActivity :
    BaseActivity<ActivityMemberRechargeRecordBinding>(R.layout.activity_member_recharge_record) {
    private val model: MemberRechargeRecordViewModel by viewModels()
    private var memberPhone: String? = null
    private val mAdapter by lazy { MemberRechargeRecordAdapter() }
    override fun initData() {
        memberPhone = intentStringExtras(CommonConstant.MEMBER_PHONE)
        binding.titleBar.setOnBack(this)
        binding.recyclerView.bind(mAdapter) {
            onDeleteClickListener = { bean, i ->
                model.deleteRecharges(bean.id, i)
            }
            setOnItemClickListener { _, _, position ->
                startActivity<RecordDetailsActivity>("record_id" to data[position].id, "type" to 1)
            }
            setEmptyView(R.layout.view_empty)
        }
        model.apply {
            findRecharges(memberPhone)
            memberRechargeRecordBean.observe(this@MemberRechargeRecordActivity) {
                mAdapter.setList(it)
            }
            deletePosition.observe(this@MemberRechargeRecordActivity) {
                mAdapter.removeAt(it)
            }
        }
    }
}