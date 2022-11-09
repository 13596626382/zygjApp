package com.tx.zygj.bean

data class PaymentMethodBean(
    val type: String,
    val balance: String? = "0",
    var isCheck: Boolean = false,
)