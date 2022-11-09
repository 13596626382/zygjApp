package com.tx.zygj.bean

data class StatisticsBean(
    val orders: Int,
    val sale: String = "0.00",
    val members: Int,
    val money: String = "0.00",
)