package com.tx.zygj.ui.change

import com.tx.zygj.api.BaseRepository

class ChangePassRepository : BaseRepository() {

    suspend fun changePass(
        phone: String?,
        passWord: String,
        newPassWord: String,
        confirmPassword: String
    ) = retrofit.changePass(
        phone, passWord, newPassWord, confirmPassword
    )
}