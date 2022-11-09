package com.tx.zygj.ui.member.information

import com.tx.zygj.api.BaseRepository

class MemberInformationRepository : BaseRepository(){

    suspend fun getMemBerMsg(id: Int?) = retrofit.getMemBerMsg(id)
}