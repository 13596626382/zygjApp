package com.tx.zygj.ui.scan

import com.tx.zygj.api.BaseRepository

class ScanCodeRepository : BaseRepository() {

    suspend fun findByPhone(phone: String) = retrofit.findByPhone(phone)
}