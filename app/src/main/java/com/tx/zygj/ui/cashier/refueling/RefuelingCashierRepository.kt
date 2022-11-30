package com.tx.zygj.ui.cashier.refueling

import com.tx.zygj.api.BaseRepository

class RefuelingCashierRepository : BaseRepository(){

    suspend fun getOleice() = retrofit.getOleice()

    suspend fun getOilGun(id: Int?) = retrofit.getOilGun(id)

    suspend fun getOiler() = retrofit.getOiler()

    suspend fun getMemBerMsg(id: Int?) = retrofit.getMemBerMsg(id)
}