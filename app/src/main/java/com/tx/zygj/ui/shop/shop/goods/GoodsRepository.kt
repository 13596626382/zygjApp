package com.tx.zygj.ui.shop.shop.goods

import com.tx.zygj.api.BaseRepository

class GoodsRepository : BaseRepository() {

    suspend fun getGoods() = retrofit.getGoodsAndGift()
}