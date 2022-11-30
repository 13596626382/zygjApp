package com.tx.zygj.ui.oil

import com.tx.zygj.api.BaseRepository
import okhttp3.RequestBody

class OilSettingRepository : BaseRepository() {

    suspend fun getOleice() = retrofit.getOleice()


    suspend fun updateOleic(body: RequestBody) = retrofit.updateOleic(body)
}