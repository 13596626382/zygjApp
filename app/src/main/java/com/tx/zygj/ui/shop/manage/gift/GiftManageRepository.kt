package com.tx.zygj.ui.shop.manage.gift

import com.tx.zygj.api.BaseRepository

class GiftManageRepository : BaseRepository() {

    suspend fun getGoodsClassify() = retrofit.getGoodsClassify(2)

    suspend fun getGoods(goodsClassifyId: Int?, page: Int) =
        retrofit.getGoods(goodsClassifyId = goodsClassifyId, page = page)

    suspend fun addGoodsClassify(classifyName: String) = retrofit.addGoodsClassify(classifyName, 2)
}