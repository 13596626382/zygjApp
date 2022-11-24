package com.tx.zygj.dialog.shop

import com.tx.zygj.api.BaseRepository
import okhttp3.MultipartBody

class ManageShopRepository : BaseRepository() {

    suspend fun uploadImage(file: MultipartBody.Part) = retrofit.uploadImage(file)

    suspend fun addGoods(
        goodsPicture: String,
        barCode: String?,
        goodsName: String?,
        goodsClassifyId: Int?,
        sellingPrice: Double?,
        marketPrice: Double?,
        costPrice: Double?,
        goodsUnit: String?,
        bonusPoints: Int?,
        stock: Int?,
        publishOrNot: Int?,
        mallRelease: Int?,
        recommend: Int?,
        hotOrNot: Int?,
        goodsDescribe: String?,
        goodsOrGift: Int
    ) = retrofit.addGoods(
        goodsPicture,
        barCode,
        goodsName,
        goodsClassifyId,
        sellingPrice,
        marketPrice,
        costPrice,
        goodsUnit,
        bonusPoints,
        stock,
        publishOrNot,
        mallRelease,
        recommend,
        hotOrNot,
        goodsDescribe,
        goodsOrGift = goodsOrGift
    )

    suspend fun updateGoods(
        id: Int?,
        goodsPicture: String?,
        barCode: String?,
        goodsName: String?,
        goodsClassifyId: Int?,
        sellingPrice: Double?,
        marketPrice: Double?,
        costPrice: Double?,
        goodsUnit: String?,
        bonusPoints: Int?,
        stock: Int?,
        publishOrNot: Int?,
        mallRelease: Int?,
        recommend: Int?,
        hotOrNot: Int?,
        goodsDescribe: String?,
        goodsOrGift: Int
    ) = retrofit.updateGoods(
        id,
        goodsPicture,
        barCode,
        goodsName,
        goodsClassifyId,
        sellingPrice,
        marketPrice,
        costPrice,
        goodsUnit,
        bonusPoints,
        stock,
        publishOrNot,
        mallRelease,
        recommend,
        hotOrNot,
        goodsDescribe,
        goodsOrGift = goodsOrGift
    )
}