package com.tx.zygj.ui.oil

import com.tx.zygj.api.BaseRepository
import okhttp3.RequestBody

class OilSettingRepository : BaseRepository() {

    suspend fun getOleice(id: Int?) = retrofit.getOleice(id)


    suspend fun updateOleic(body: RequestBody) = retrofit.updateOleic(body)
}