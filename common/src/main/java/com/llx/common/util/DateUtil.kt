package com.llx.common.util

import java.text.SimpleDateFormat
import java.util.*

fun toCurrentDate(): String = SimpleDateFormat(
    "yyyy-MM",
    Locale.getDefault(Locale.Category.FORMAT)
).format(Date(System.currentTimeMillis()))

fun toYMDCurrentDate(): String = SimpleDateFormat(
    "yyyy-MM-dd",
    Locale.getDefault(Locale.Category.FORMAT)
).format(Date(System.currentTimeMillis()))

fun toCurrentYMDHMSDate(): String = SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss",
    Locale.getDefault(Locale.Category.FORMAT)
).format(Date(System.currentTimeMillis()))

fun Date.toYMDHMSDate(): String = SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss",
    Locale.getDefault(Locale.Category.FORMAT)
).format(this)

fun Date.toYMDateString(): String =
    SimpleDateFormat("yyyy-MM", Locale.getDefault(Locale.Category.FORMAT)).format(this)

fun Date.toYMDDateString(): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT)).format(this)

//时间戳转日期
fun String.timeStampToString(): String {
    val date = Date(this.toLong())
    return SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.getDefault(Locale.Category.FORMAT)
    ).format(date)
}