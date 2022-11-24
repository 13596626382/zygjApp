package com.llx.common.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Double计算保留最后两位小数
 */
fun String.division(other: Double): Double =
    BigDecimal(toDouble().div(other)).setScale(2, RoundingMode.HALF_UP).toDouble()

fun Double?.plus1(double: Double?): Double? = if (double == null) this else this?.plus(double)

fun Double?.minus1(double: Double?): Double? = if (double == null) this else this?.minus(double)

fun Double?.contrast(double: Double): Boolean = if (this == null) false else this == double
