package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberManageBean(
    val applicant: String? = null,
    var letters: String? = null,
    val avatarUrl: String? = null,
    var birthday: String? = null,
    var carNumber: String? = null,
    var memberType: Int = 0,
    val code: String? = null,
    val consume: String? = null,
    val dieselCard: String? = null,
    var firm: String? = null,
    val gasolineCard: String? = null,
    var gender: String? = null,
    val id: Int = 0,
    val coupon: String? = null,
    val integral: String? = null,
    val lastLoginDate: String? = null,
    val naturalGas: String? = null,
    var nickName: String? = null,
    val openId: String? = null,
    var password: String? = null,
    var phone: String? = null,
    val place: String? = null,
    val purse: String? = null,
    var recommend: String? = null,
    val registerDate: String? = null,
    var state: String? = null
) : Parcelable

