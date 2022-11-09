package com.tx.zygj.ui.record

import com.tx.zygj.api.BaseRepository

class RecordDetailsRepository : BaseRepository() {

    suspend fun getConsumeRecord(id: Int) = retrofit.getConsumeRecord(id)

    suspend fun getRechargeRecord(id: Int) = retrofit.getRechargeRecord(id)
}