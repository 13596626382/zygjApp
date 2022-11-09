package com.tx.zygj.ui.member.consume

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.AttachPopupView
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityMemberConsumeRecordBinding
import com.tx.zygj.ui.record.RecordDetailsActivity

/**
 * 消费记录
 */
class MemberConsumeActivity :
    BaseActivity<ActivityMemberConsumeRecordBinding>(R.layout.activity_member_consume_record) {

    private val model: MemberConsumeViewModel by viewModels()
    private val mAdapter by lazy { MemberConsumeAdapter() }

    private var memberPhone: String? = null
    private var currentDate = ""
    override fun initData() {
        binding.titleBar.setOnBack(this)
        memberPhone = intentStringExtras(CommonConstant.MEMBER_PHONE)
        currentDate = toCurrentDate()
        binding.date.text = currentDate
        binding.recyclerView.bind(mAdapter) {
            setOnItemLongClickListener { _, view, position ->
                val attachPopupView: AttachPopupView = XPopup.Builder(context)
                    .isCoverSoftInput(true)
                    .atView(view) // 依附于所点击的View，内部会自动判断在上方或者下方显示
                    .asAttachList(
                        arrayOf("删除"),
                        intArrayOf(),
                        { _, _ ->
                            model.deleteOrder(mAdapter.data[position].id, position)
//                            removeAt(position)
                        },
                        0,
                        0 /*, Gravity.LEFT*/
                    )
                attachPopupView.show()
                true
            }

            setOnItemClickListener { _, _, position ->
                startActivity<RecordDetailsActivity>("record_id" to data[position].id, "type" to 0)
            }
        }.setEmptyView(R.layout.view_empty)
        binding.selectDate.showDateDialog(true) {
            binding.date.text = it.toYMDateString()
            currentDate = it.toYMDateString()
            model.getOrderList(memberPhone, it.toYMDateString())
        }

        model.apply {
            getOrderList(memberPhone, currentDate)
            consumeBean.observe(this@MemberConsumeActivity) {
                mAdapter.setList(it)
            }
            deletePosition.observe(this@MemberConsumeActivity) {
                mAdapter.removeAt(it)
            }
        }

    }
}