package com.tx.zygj.ui.cashier.fast

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.PaySuccessBean

class FastCashierViewModel : BaseViewModel() {
    private val repository by lazy { FastCashierRepository() }
    var orderNo = MutableLiveData<String>()
    var paySuccessBean = MutableLiveData<PaySuccessBean>()
    fun getFastOrder(actual: Double) {
        launch {
            val data = repository.getFastOrder(actual)
            if (data.code == 0) {
                orderNo.value = data.getData()
                CommonConstant.setOrderNo(data.getData())
            }
        }
    }


    fun fastPay(authCode: String, tradeNo: String, total: Double) {
        launch {
            val data = repository.fastPay(authCode, tradeNo, total)
            if (data.status == 200) {
                paySuccessBean.value = data.getData()
            } else {
                toast(data.message)
            }
            requestResult.value = data.status == 200
        }
    }
}