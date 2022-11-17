package com.llx.common.util

/**
 * 字符串拼接
 * stringBuild("a", 0, false, 0.00, "c")
 * 结果 -> a0false0.00c
 */
fun stringBuild(vararg s: Any?) = buildString { append(*s) }

//截取小数点后两位,如果是00返回截取后的字符串，不是返回原字符串
fun String.subPoint() = if (substring(indexOf("."), length) == ".00") {
    substring(0, indexOf("."))
} else this