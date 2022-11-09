package com.tx.zygj.bean

data class MemberRechargeRecordBean (
    val id: Int,
    val cardType: Int,
    val rechargeDate: String,
    val orderNo: String,
    val money: Double,
    val openId: String,
    val status: Int,
    val rechargeType: String,
        )