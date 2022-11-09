package com.tx.zygj.ui.member.manage

import com.tx.zygj.api.BaseRepository

class MemberManageRepository  : BaseRepository() {

    suspend fun getMember() = retrofit.getMember()
}