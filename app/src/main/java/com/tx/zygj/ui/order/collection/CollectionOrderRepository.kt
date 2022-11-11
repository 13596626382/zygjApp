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
        gunNumber: Int?,
        integral: Int?,
        discount: Double?
    ) = retrofit.generateOrder(
        model,
        typeId,
        gasId,
        gasMan,
        actual,
        totalPrice,
        memberPhone,
        payModel,
        oilsRise,
        oilPrice,
        gunNumber,
        integral,
        discount
    )

    suspend fun getRefuelingDiscount(
        totalPrice: Double?, oilId: Int?, memberId: Int?
    ) = retrofit.getRefuelingDiscount(totalPrice, oilId, memberId)

    suspend fun play(authCode: String, tradeNo: String, total: Double) =
        retrofit.pay(authCode, tradeNo, total)

    suspend fun cardPayment(
        orderNo: String,
        memberPhone: String?,
        actual: Double,
        integral: Int,
    ) =
        retrofit.cardPayment(orderNo, memberPhone, actual, integral)

    suspend fun oidCardPayment(
        orderNo: String, memberPhone: String?, actual: Double, integral: Int, typeId: Int?
    ) = retrofit.oidCardPayment(orderNo, memberPhone, actual, integral, typeId)
}