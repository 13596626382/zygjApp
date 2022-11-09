package com.tx.zygj.ui.cashier.fast

import com.llx.common.CommonConstant
import com.tx.zygj.api.BaseRepository
class FastCashierRepository : BaseRepository(){

    suspend fun getFastOrder(actual: Double) = retrofit.getFastOrder(Actual = actual, gasId = CommonConstant.getUserInfo()?.gasId)

    suspend fun fastPay(authCode: String, tradeNo: String, total: Double) =
        retrofit.fastPay(authCode, tradeNo, total)
}