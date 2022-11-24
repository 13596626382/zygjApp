package com.tx.zygj.ui.cashier.goods

import com.tx.zygj.api.BaseRepository

class GoodsCashierRepository : BaseRepository() {

    suspend fun getGoodsOrderNo(
        consumptionType: String,
        memberId: Int?,
        consumptionMoneyIntegral: String,
        goodsJson: String
    ) =
        retrofit.getGoodsOrderNo(
            consumptionType = consumptionType,
            memberId = memberId,
            goodsOrGift = 1,
            consumptionMoneyIntegral = consumptionMoneyIntegral,
            goodsMap = goodsJson
        )

    suspend fun goodsCurrencyPayment(goodsOrderNo: String, goodsMap: String) =
        retrofit.goodsCurrencyPayment(goodsOrderNo, goodsMap)


    suspend fun goodsPay(authCode: String, tradeNo: String, total: Double, goodsMap: String) =
        retrofit.goodsPay(authCode, tradeNo, total, goodsMap = goodsMap)

    suspend fun goodsCashPayment(goodsOrderNo: String, goodsMap: String) =
        retrofit.goodsCashPayment(goodsOrderNo, goodsMap)
}