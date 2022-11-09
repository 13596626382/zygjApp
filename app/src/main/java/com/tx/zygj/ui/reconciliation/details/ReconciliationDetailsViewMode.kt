package com.tx.zygj.ui.reconciliation.details

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.PaySuccessBean

class ReconciliationDetailsViewMode : BaseViewModel() {
    private val repository by lazy { ReconciliationDetailsRepository() }
    val paySuccessBean = MutableLiveData<PaySuccessBean>()
    fun getOrderTodayDetails(id: Int, typeId: String?) {
        launch {
            val data = repository.getOrderTodayDetails(id, typeId)
            if (data.code == 0) {
                paySuccessBean.value = data.getData()
            }
        }
    }
}