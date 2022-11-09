package com.tx.zygj.ui.car.manage

import com.tx.zygj.api.BaseRepository

class CarBrandManageRepository : BaseRepository(){

    suspend fun getCarNumber(phone: String?) =
        retrofit.getCarNumber(phone)



}