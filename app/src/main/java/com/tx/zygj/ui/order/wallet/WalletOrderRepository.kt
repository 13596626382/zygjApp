package com.tx.zygj.ui.order.wallet

import com.tx.zygj.api.BaseRepository

class WalletOrderRepository : BaseRepository() {

    suspend fun findOrderNo(
        cardType: Int,
        memberPhone: String?,
        money: Double?,
        gesId: Int?,
        gasMan: String?,
        give: Double?,
        receivable: Double?,
        giveIntegral: Int?,
    ) = retrofit
        .findOrderNo(
            cardType,
            memberPhone,
            money,
            gesId,
            gasMan,
            give,
            receivable,
            giveIntegral
        )

    suspend fun getRechargeActivity(memberId: Int?) = retrofit.getRechargeActivity(memberId)

    suspend fun getRechargeActivityDetails(giftId: Int?, money: Double) =
        retrofit.getRechargeActivityDetails(giftId, money)

    suspend fun play(authCode: String, tradeNo: String, total: Double) =
        retrofit.pay(authCode, tradeNo, total, 1)

}