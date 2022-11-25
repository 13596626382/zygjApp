package com.llx.common.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 计算保留最后两位小数
 */
fun Double.division(other: Double): Double = toDouble().div(other).decimal()

/**
 * 加
 */
fun Double?.plus1(double: Double?): Double? =
    if (double == null) this else this?.plus(double)?.decimal()

/**
 * 减
 */
fun Double?.minus1(double: Double?): Double? =
    if (double == null) this else this?.minus(double)?.decimal()

/**
 * 对比是否相等
 */
fun Double?.contrast(double: Double): Boolean = if (this == null) false else this == double


/**
 * 保留两位小数
 */
fun Double.decimal(): Double =
    BigDecimal(this).setScale(2, RoundingMode.HALF_UP).toDouble()