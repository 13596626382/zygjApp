package com.tx.zygj.ui.order.collection

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.PaySuccessBean

class CollectionOrderViewModel : BaseViewModel() {
    private val repository by lazy { CollectionOrderRepository() }
    var orderNo = MutableLiveData<String>()
    var paySuccessBean = MutableLiveData<PaySuccessBean>()


    fun generateOrder(
        model: String?, typeId: Int?,
        gasMan: String?, actual: Double, totalPrice: Double,
        memberPhone: String?, payModel: String, oilsRise: Double, oilPrice: Double?,
        gunNumber: Int?
    ) {
        launch {
            val data = repository.generateOrder(
                model, typeId, CommonConstant.getUserInfo()?.gasId,
                gasMan, actual, totalPrice,
                memberPhone, payModel, oilsRise, oilPrice,
                gunNumber
            )
            if (data.code == 0) {
                orderNo.value = data.getData()
                CommonConstant.setOrderNo(data.getData())
            } else {
                toast(data.msg)

            }
            requestResult.value = data.code == 0
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

    fun cardPayment(orderNo: String, memberPhone: String?, actual: Double) {
        launch {
            val data = repository.cardPayment(orderNo, memberPhone, actual)
            if (data.code == 0) {
                paySuccessBean.value = data.getData()
            } else {
                toast(data.code)
            }
            requestResult.value = data.code == 0
        }
    }

    fun oidCardPayment(orderNo: String, memberPhone: String?, actual: Double, typeId: Int?) {
        launch {
            val data = repository.oidCardPayment(orderNo, memberPhone, actual, typeId)
            if (data.code == 0) {
                paySuccessBean.value = data.getData()
            } else {
                toast(data.code)
            }
            requestResult.value = data.code == 0
        }
    }
}