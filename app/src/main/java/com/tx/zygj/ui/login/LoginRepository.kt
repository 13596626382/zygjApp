package com.tx.zygj.ui.login

import com.tx.zygj.api.BaseRepository

class LoginRepository  : BaseRepository() {
    suspend fun login(phone: String, passWord: String) =
        retrofit.login(phone, passWord)
}