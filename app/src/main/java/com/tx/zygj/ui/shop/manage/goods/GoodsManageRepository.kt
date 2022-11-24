package com.tx.zygj.ui.shop.manage.goods

import com.tx.zygj.api.BaseRepository

class GoodsManageRepository : BaseRepository() {

    suspend fun getGoodsClassify() = retrofit.getGoodsClassify(1)

    suspend fun getGoods(goodsClassifyId: Int?, page: Int) =
        retrofit.getGoods(goodsClassifyId = goodsClassifyId, page = page)

    suspend fun addGoodsClassify(classifyName: String) = retrofit.addGoodsClassify(classifyName, 1)
}