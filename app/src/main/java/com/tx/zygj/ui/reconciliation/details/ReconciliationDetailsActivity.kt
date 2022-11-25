package com.tx.zygj.ui.reconciliation.details

import android.view.View
import androidx.activity.viewModels
import com.llx.common.base.BaseActivity
import com.llx.common.util.division
import com.llx.common.util.intentIntExtras
import com.llx.common.util.intentStringExtras
import com.llx.common.util.stringBuild
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityReconciliationDetailsBinding

//对账单详情
class ReconciliationDetailsActivity :
    BaseActivity<ActivityReconciliationDetailsBinding>(R.layout.activity_reconciliation_details) {
    private val model: ReconciliationDetailsViewMode by viewModels()
    private var typeId = ""
    override fun initData() {
        binding.titleBarView.setOnBack(this)
        typeId = intentStringExtras("typeId")!!

        model.getOrderTodayDetails(intentIntExtras("id"), typeId)
        if (typeId == "消费") {
            binding.recharge.visibility = View.GONE
        } else {
            binding.consume.visibility = View.GONE
        }
        model.paySuccessBean.observe(this) {
            binding.paySuccessBean = it
            if (typeId == "消费") {
                calculation(it.actual!!,it.oilPrice!!)
            }
            binding.rechargeType.text = when (it?.cardType) {
                0 -> "汽油卡"
                1 -> "柴油卡"
                2 -> "通用钱包"
                else -> "天然气"
            }
        }
    }

    //计算加油数量
    private fun calculation(price: String, actual: String) {
        var value = price
        if (value == "") {
            value = "0"
        }
        binding.oilsRise.text = stringBuild(value.toDouble().division(actual.toDouble()), "L")
    }
}