package com.tx.zygj.ui.reconciliation

import com.tx.zygj.api.BaseRepository

class ReconciliationRepository : BaseRepository() {

    suspend fun getOrderToday() = retrofit.getOrderToday()

    suspend fun getOrderTodayLike(by: String) = retrofit.getOrderTodayLike(by)
}