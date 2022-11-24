package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopBean(
    val gift: ArrayList<ShopClassifyBean>,
    val goods: ArrayList<ShopClassifyBean>,
) : Parcelable {
    @Parcelize
    data class ShopClassifyBean(
        val name: String,
        val goodsList: ArrayList<GoodsBean>,
        var isCheck: Boolean
    ) : Parcelable
}