package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OilBean(
    val type: String,
    val oil: ArrayList<OilModelBean>,
    var isCheck: Boolean = false,
) : Parcelable {
    @Parcelize
    data class OilModelBean(
        var isCheck: Boolean = false,
        var model: String = "",
        var name: String = "",
        var typeId: Int = 0,
        var id: Int = 0,
        var gasId: Int = 0,
        var state: Int = 0,
        var price: Double = 0.00
    ) : Parcelable
}