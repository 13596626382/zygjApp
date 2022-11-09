package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OilerBean(
    val id: Int,
    val name: String,
    val gas_id: Int
) : Parcelable