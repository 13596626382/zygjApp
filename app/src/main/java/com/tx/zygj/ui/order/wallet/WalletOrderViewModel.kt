package com.tx.zygj.ui.order.wallet

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.PaySuccessBean

class WalletOrderViewModel : BaseViewModel() {
    private val repository by lazy { WalletOrderRepository() }
    var orderNo = MutableLiveData<String>()
    var paySuccessBean = MutableLiveData<PaySuccessBean>()

    fun findOrderNo(cardType: Int, memberPhone: String?, money: Double, gasMan: String?) {
        launch {
            val data = repository.findOrderNo(cardType, memberPhone, money, gasMan)
            if (data.code == 0) {
                orderNo.value = data.getData()
                CommonConstant.setOrderNo(data.getData())
            } else {
                toast(data.msg)
            }
        }
    }

    fun play(authCode: String, tradeNo: String, total: Double) {
        launch {
            val data = repository.play(authCode, tradeNo, total)
            if (data.status == 200) {
                paySuccessBean.value = data.getData()
            } else {
                toast(data.message)
            }
            requestResult.value = data.status == 200
        }
    }

}