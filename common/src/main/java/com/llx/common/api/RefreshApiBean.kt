package com.llx.common.api

data class RefreshApiBean<T>(
    val data: ArrayList<T>,
    val pageCount: Int,
    val code: Int,
    val pageCurrent: Int,
)