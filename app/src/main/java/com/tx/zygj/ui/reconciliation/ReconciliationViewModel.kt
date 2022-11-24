package com.tx.zygj.ui.reconciliation

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.ReconciliationBean

class ReconciliationViewModel : BaseViewModel() {
    private val repository by lazy { ReconciliationRepository() }
    val reconciliationBean = MutableLiveData<ArrayList<ReconciliationBean>>()

    fun getOrderToday() {
        launch {
            val data = repository.getOrderToday()
            if (data.status == 500) {
                toast(data.message)
            }
            if (data.code == 0) {
                reconciliationBean.value = data.getData()
            }
            requestResult.value = data.code == 0
        }
    }

    fun getOrderTodayLike(by: String) {
        launch {
            val data = repository.getOrderTodayLike(by)
            if (data.code == 0) {
                reconciliationBean.value = data.getData()
            }
            if (data.status == 500) {
                toast(data.message)
            }
            requestResult.value = data.code == 0
        }
    }
}