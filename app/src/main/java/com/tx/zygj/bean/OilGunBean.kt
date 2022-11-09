package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OilGunBean(
    val id: Int,
    val oilType: Int,
    val oilGunNumber: Int,
    var isCheck: Boolean = false,
) : Parcelable