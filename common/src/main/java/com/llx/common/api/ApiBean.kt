package com.llx.common.api

data class ApiBean<T>(
    private val data: T,
    val msg: String,
    val message: String,
    val code: Int = -1,
    val status: Int,
) {
    fun getData() = data
}