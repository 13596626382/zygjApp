package com.tx.zygj.ui.main.fragment.work

import com.tx.zygj.api.BaseRepository
class WorkRepository  : BaseRepository() {

    suspend fun getStatistics() = retrofit.getStatistics()

    suspend fun getPrintSettings() = retrofit.getPrintSettings()
}