package com.tx.zygj.ui.reconciliation.details

import com.tx.zygj.api.BaseRepository

class ReconciliationDetailsRepository : BaseRepository() {

    suspend fun getOrderTodayDetails(id: Int, typeId: String?) = retrofit.getOrderTodayDetails(id, typeId)
}