package com.tx.zygj.ui.personal

import com.tx.zygj.api.BaseRepository

class PersonalRepository : BaseRepository() {


    suspend fun updatePersonal(
        id: Int?,
        userName: String?,
        name: String?,
        phone: String?,
        workNumber: String?
    ) =
        retrofit
            .updatePersonal(id, userName, name, phone, workNumber)

}