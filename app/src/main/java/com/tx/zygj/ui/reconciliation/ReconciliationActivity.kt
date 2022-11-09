package com.tx.zygj.ui.reconciliation

import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.ReconciliationBean
import com.tx.zygj.databinding.ActivityReconciliationBinding
import com.tx.zygj.ui.reconciliation.details.ReconciliationDetailsActivity

//对账单
class ReconciliationActivity :
    BaseActivity<ActivityReconciliationBinding>(R.layout.activity_reconciliation) {
    private val model: ReconciliationViewModel by viewModels()
    private val mAdapter by lazy { ReconciliationAdapter() }

    private var reconciliationListBean = ArrayList<ReconciliationBean>()
    private var searchContent = ""
    private var searchType = 0

    override fun initData() {
        binding.titleBar.setOnBackActivity(this)

        binding.choice.showDialog(arrayListOf("全部", "消费", "充值", "快速收银"), this) { s, i ->
            binding.choiceText.text = s
            searchType = i
            screen()
        }
        binding.recyclerView.bind(mAdapter) {
            setOnItemClickListener { _, _, position ->
                if (data[position].memberPhone == null) {
                    toast("此订单为快速收银订单")
                    return@setOnItemClickListener
                }
                startActivity<ReconciliationDetailsActivity>(
                    "id" to data[position].id,
                    "typeId" to data[position].typeId
                )
            }
            setEmptyView(layoutInflater.inflate(R.layout.view_empty, null, false))
        }
        binding.editText.setOnEditorActionListener { _, _, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                searchContent = binding.editText.textString()
                search()
            }
            event.keyCode == KeyEvent.KEYCODE_ENTER
        }
        binding.search.setOnSingleClickListener {
            searchContent = binding.editText.textString()
            search()
        }
        model.getOrderToday()
        model.reconciliationBean.observe(this) {
            reconciliationListBean.clear()
            reconciliationListBean.addAll(it)
            screen()
        }
        model.requestResult.observe(this) {
            binding.progressBae.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    //筛选
    private fun screen() {
        mAdapter.setList(when (searchType) {
            0 -> reconciliationListBean
            1 -> reconciliationListBean.filter { it1 -> it1.typeId == "消费" }
            2 -> reconciliationListBean.filter { it1 -> it1.typeId == "充值" }
            else -> reconciliationListBean.filter { it1 -> it1.memberPhone == null }
        })
    }

    private fun search() {
        model.getOrderTodayLike(searchContent)
    }
}