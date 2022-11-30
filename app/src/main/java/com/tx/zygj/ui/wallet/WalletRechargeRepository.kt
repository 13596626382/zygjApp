package com.tx.zygj.ui.wallet

import com.tx.zygj.api.BaseRepository

class WalletRechargeRepository : BaseRepository() {

    suspend fun getOiler() = retrofit.getOiler()
}