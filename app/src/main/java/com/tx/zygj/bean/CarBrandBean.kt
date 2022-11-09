package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarBrandBean(
    val id: Int? = 0,
    var carName: String? = "",
    var carNumber: String? = "",
    val phone: String? = "",
    var state: Int? = 0
) : Parcelable