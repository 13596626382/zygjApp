package com.tx.zygj.ui.record

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.tx.zygj.bean.PaySuccessBean

class RecordDetailsViewModel : BaseViewModel() {
    private val repository by lazy { RecordDetailsRepository() }
    val paySuccessBean = MutableLiveData<PaySuccessBean>()
    fun getConsumeRecord(id: Int) {
        launch {
            val data = repository.getConsumeRecord(id)
            if (data.code == 0) {
                paySuccessBean.value = data.getData()
            }
        }
    }
    fun getRechargeRecord(id: Int) {
        launch {
            val data = repository.getRechargeRecord(id)
            if (data.code == 0) {
                paySuccessBean.value = data.getData()
            }
        }
    }
}