package com.llx.common.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Double计算保留最后两位小数
 */
fun String.division(other: Double): Double =
    BigDecimal(toDouble().div(other)).setScale(2, RoundingMode.HALF_UP).toDouble()


