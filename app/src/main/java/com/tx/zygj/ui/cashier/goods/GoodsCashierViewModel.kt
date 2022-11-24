package com.tx.zygj.ui.cashier.goods

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast

class GoodsCashierViewModel : BaseViewModel() {
    private val repository by lazy { GoodsCashierRepository() }

    val goodsOrderNo = MutableLiveData<String>()
    fun getGoodsOrderNo(
        consumptionType: String,
        memberId: Int?,
        consumptionMoneyIntegral: String,
        goodsJson: String
    ) {
        launch {
            val data = repository.getGoodsOrderNo(
                consumptionType,
                memberId,
                consumptionMoneyIntegral,
                goodsJson
            )
            if (data.code == 0) {
                goodsOrderNo.value = data.getData()
            } else {
                toast(data.msg)
                requestResult.value = false
            }
        }
    }

    fun goodsCurrencyPayment(goodsOrderNo: String, goodsMap: String) {
        launch {
            val data = repository.goodsCurrencyPayment(goodsOrderNo, goodsMap)
            if (data.code != 0) {
                toast(data.msg)
            }
            requestResult.value = data.code == 0
        }
    }

    fun goodsPay(authCode: String, tradeNo: String, total: Double, goodsJson: String) {
        launch {
            val data = repository.goodsPay(authCode, tradeNo, total, goodsJson)
            if (data.status != 200) {
                toast(data.message)
            }
            requestResult.value = data.status == 200
        }
    }

    fun goodsCashPayment(goodsOrderNo: String, goodsMap: String) {
        launch {
            val data = repository.goodsCashPayment(goodsOrderNo, goodsMap)
            if (data.code != 0) {
                toast(data.msg)
            }
            requestResult.value = data.code == 0
        }
    }
}