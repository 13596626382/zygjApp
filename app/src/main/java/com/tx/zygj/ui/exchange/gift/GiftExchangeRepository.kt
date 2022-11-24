package com.tx.zygj.ui.exchange.gift

import com.tx.zygj.api.BaseRepository

class GiftExchangeRepository : BaseRepository() {

    suspend fun getGoodsOrderNo(
        memberId: Int?,
        consumptionMoneyIntegral: String,
        goodsJson: String
    ) =
        retrofit.getGoodsOrderNo(
            consumptionType = "积分兑换",
            memberId = memberId,
            goodsOrGift = 2,
            consumptionMoneyIntegral = consumptionMoneyIntegral,
            goodsMap = goodsJson
        )

    suspend fun giftPayment(goodsOrderNo: String, goodsMap: String) =
        retrofit.giftPayment(goodsOrderNo, goodsMap)
}