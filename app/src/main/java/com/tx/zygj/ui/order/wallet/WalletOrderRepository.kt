package com.tx.zygj.ui.order.wallet

import com.llx.common.CommonConstant
import com.tx.zygj.api.BaseRepository

class WalletOrderRepository : BaseRepository() {

    suspend fun findOrderNo(
        cardType: Int,
        memberPhone: String?,
        money: Double,
        gasMan: String?,
    ) = retrofit
        .findOrderNo(
            cardType,
            memberPhone,
            money,
            CommonConstant.getUserInfo()?.gasId,
            gasMan,
            receivable = money
        )

    suspend fun getRechargeActivity(memberId: Int?) = retrofit.getRechargeActivity(memberId)

    suspend fun play(authCode: String, tradeNo: String, total: Double) =
        retrofit.pay(authCode, tradeNo, total, 1)

}