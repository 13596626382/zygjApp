package com.tx.zygj.ui.cashier.refueling

import com.tx.zygj.api.BaseRepository

class RefuelingCashierRepository : BaseRepository(){

    suspend fun getOleice(id: Int?) = retrofit.getOleice(id)

    suspend fun getOilGun(id: Int?) = retrofit.getOilGun(id)

    suspend fun getOiler(id: Int?) = retrofit.getOiler(id)

    suspend fun getMemBerMsg(id: Int?) = retrofit.getMemBerMsg(id)
}