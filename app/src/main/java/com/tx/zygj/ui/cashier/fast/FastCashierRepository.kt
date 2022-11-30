package com.tx.zygj.ui.cashier.fast

import com.tx.zygj.api.BaseRepository
class FastCashierRepository : BaseRepository(){

    suspend fun getFastOrder(actual: Double) = retrofit.getFastOrder(actual)

    suspend fun fastPay(authCode: String, tradeNo: String, total: Double) =
        retrofit.pay(authCode, tradeNo, total, 2)
}