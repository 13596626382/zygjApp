package com.tx.zygj.ui.member.register

import com.tx.zygj.api.BaseRepository

class MemberRegisterRepository : BaseRepository() {


    suspend fun addMember(
        nickName: String?,
        phone: String?,
        birthday: String?,
        memberType: Int?,
        carNumber: String?,
        firm: String?,
        gender: String?,
        state: String?,
        password: String?,
        recommend: String?,
        applicant: String,
        place: String,
    ) = retrofit.addMember(
        nickName,
        phone,
        birthday,
        memberType,
        carNumber,
        firm,
        gender,
        state,
        password,
        recommend,
        applicant,
        place
    )


    suspend fun updateMember(
        id: Int?,
        nickName: String?,
        phone: String?,
        birthday: String?,
        memberType: Int?,
        carNumber: String?,
        firm: String?,
        gender: String?,
        state: String?,
        password: String?,
        recommend: String?,
        applicant: String,
        place: String,
    ) = retrofit.updateMember(
        id,
        nickName,
        phone,
        birthday,
        memberType,
        carNumber,
        firm,
        gender,
        state,
        password,
        recommend,
        applicant,
        place
    )
}