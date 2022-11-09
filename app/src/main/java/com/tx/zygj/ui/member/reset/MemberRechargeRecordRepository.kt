package com.tx.zygj.ui.member.reset

import com.tx.zygj.api.BaseRepository

class MemberRechargeRecordRepository : BaseRepository() {

    suspend fun findRecharges(memberPhone: String?) = retrofit.findRecharges(memberPhone)

    suspend fun deleteRecharges(id: Int) = retrofit.deleteRecharges(id)
}