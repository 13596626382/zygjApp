package com.tx.zygj.ui.member.consume

import com.tx.zygj.api.BaseRepository

class MemberConsumeRepository : BaseRepository() {

   suspend fun getOrderList(memberPhone: String?, years: String?) = retrofit.getOrderList(memberPhone, years)

   suspend fun deleteOrder(id: Int) = retrofit.deleteOrder(id)
}