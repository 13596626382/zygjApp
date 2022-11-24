package com.tx.zygj.ui.exchange.gift

import com.llx.common.base.BaseViewModel
import com.llx.common.util.toast

class GiftExchangeViewModel : BaseViewModel() {
    private val repository by lazy { GiftExchangeRepository() }

    fun getGoodsOrderNo(
        memberId: Int?,
        consumptionMoneyIntegral: String,
        goodsJson: String
    ) {
        launch {
            val data = repository.getGoodsOrderNo(memberId, consumptionMoneyIntegral, goodsJson)
            if (data.code == 0) {
                giftPayment(data.getData(), goodsJson)
            } else {
                toast(data.msg)
                requestResult.value = false
            }
        }
    }

    private fun giftPayment(goodsOrderNo: String, goodsMap: String) {
        launch {
            val data = repository.giftPayment(goodsOrderNo, goodsMap)
            if (data.code == 0) {
                toast(data.getData())
            }
            requestResult.value = data.code == 0
        }
    }
}