package com.tx.zygj.ui.order.collection

import com.tx.zygj.api.BaseRepository

class CollectionOrderRepository : BaseRepository() {
    suspend fun generateOrder(
        model: String?,
        typeId: Int?,
        gasId: Int?,
        gasMan: String?,
        actual: Double,
        totalPrice: Double,
        memberPhone: String?,
        payModel: String,
        oilsRise: Double,
        oilPrice: Double?,
        gunNumber: Int?
    ) = retrofit.generateOrder(
        model, typeId, gasId, gasMan, actual,
        totalPrice, memberPhone, payModel, oilsRise, oilPrice,
        gunNumber
    )

    suspend fun play(authCode: String, tradeNo: String, total: Double) =
        retrofit.pay(authCode, tradeNo, total)

    suspend fun cardPayment(orderNo: String, memberPhone: String?, actual: Double) =
        retrofit.cardPayment(orderNo, memberPhone, actual)

    suspend fun oidCardPayment(orderNo: String, memberPhone: String?, actual: Double, typeId: Int?) =
        retrofit.oidCardPayment(orderNo, memberPhone, actual, typeId)
}