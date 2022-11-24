package com.tx.zygj.ui.shop.shop.gift

import com.tx.zygj.api.BaseRepository

class GiftRepository : BaseRepository() {

    suspend fun getGift() = retrofit.getGoodsAndGift()
}