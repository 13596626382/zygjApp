package com.tx.zygj.dialog.power

import com.tx.zygj.api.BaseRepository

class PowerDialogRepository : BaseRepository(){

    suspend fun handover(phone: String?, passWord: String) =
        retrofit.handover(phone, passWord)
}