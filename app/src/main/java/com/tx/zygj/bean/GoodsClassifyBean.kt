package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsClassifyBean(
    val id: Int,
    val classifyName: String,
    val display: Int,
    val gasId: Int,
    var isCheck: Boolean
) : Parcelable