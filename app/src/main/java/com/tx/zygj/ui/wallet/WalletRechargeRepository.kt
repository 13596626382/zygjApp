package com.tx.zygj.ui.wallet

import com.tx.zygj.api.BaseRepository

class WalletRechargeRepository : BaseRepository() {

    suspend fun getOiler(id: Int?) = retrofit.getOiler(id)
}