package com.tx.zygj.ui.order.collection

import androidx.lifecycle.MutableLiveData
import com.llx.common.CommonConstant
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast
import com.tx.zygj.bean.CollectionDiscountBean
import com.tx.zygj.bean.PaySuccessBean

class CollectionOrderViewModel : BaseViewModel() {
    private val repository by lazy { CollectionOrderRepository() }
    var requestOrderNo = MutableLiveData<String>()
    var collectionDiscountBean = MutableLiveData<CollectionDiscountBean>()
    var paySuccessBean = MutableLiveData<PaySuccessBean>()

    fun generateOrder(
        model: String?, typeId: Int?,
        gasMan: String?, actual: Double, totalPrice: Double,
        memberPhone: String?, payModel: String, oilsRise: Double, oilPrice: Double?,
        gunNumber: Int?, integral: Int?, discount: Double?, preferentialMethod: String?
    ) {
        launch {
            val data = repository.generateOrder(
                model, typeId, CommonConstant.getUserInfo()?.gasId,
                gasMan, actual, totalPrice,
                memberPhone, payModel, oilsRise, oilPrice,
                gunNumber, integral, discount, preferentialMethod
            )
            if (data.code == 0) {
                requestOrderNo.value = data.getData()
                CommonConstant.setOrderNo(data.getData())
            } else {
                toast(data.msg)

            }
            requestResult.value = data.code == 0
        }
    }

    fun getRefuelingDiscount(
        totalPrice: Double?, oilId: Int?, memberId: Int?
    ) {
        launch {
            val data = repository.getRefuelingDiscount(totalPrice, oilId, memberId)
            if (data.code == 0) {
                collectionDiscountBean.value = data.getData()
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

    fun cardPayment(orderNo: String, memberPhone: String?, actual: Double, integral: Int) {
        launch {
            val data = repository.cardPayment(orderNo, memberPhone, actual, integral)
            if (data.code == 0) {
                paySuccessBean.value = data.getData()
            } else {
                toast(data.code)
            }
            requestResult.value = data.code == 0
        }
    }

    fun oidCardPayment(
        orderNo: String,
        memberPhone: String?,
        actual: Double,
        integral: Int,
        typeId: Int?
    ) {
        launch {
            val data = repository.oidCardPayment(orderNo, memberPhone, actual, integral, typeId)
            if (data.code == 0) {
                paySuccessBean.value = data.getData()
            } else {
                toast(data.code)
            }
            requestResult.value = data.code == 0
        }
    }
}