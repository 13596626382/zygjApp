package com.tx.zygj.ui.record

import android.view.View
import androidx.activity.viewModels
import com.llx.common.base.BaseActivity
import com.llx.common.util.intentIntExtras
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityRecordDetailsBinding

//充值消费记录详情
class RecordDetailsActivity :
    BaseActivity<ActivityRecordDetailsBinding>(R.layout.activity_record_details) {
    private val model: RecordDetailsViewModel by viewModels()
    private var type = 0
    override fun initData() {
        type = intentIntExtras("type")
        if (type == 0) {
            binding.titleBarView.setTitle("消费详情")
            model.getConsumeRecord(intentIntExtras("record_id"))
            binding.recharge.visibility = View.GONE
        } else {
            binding.titleBarView.setTitle("充值详情")
            model.getRechargeRecord(intentIntExtras("record_id"))
            binding.consume.visibility = View.GONE
        }
        model.paySuccessBean.observe(this) {
            binding.paySuccessBean = it

            binding.rechargeType.text = when (it?.cardType) {
                0 -> "汽油卡"
                1 -> "柴油卡"
                2 -> "通用钱包"
                else -> "天然气"
            }
        }
    }
}