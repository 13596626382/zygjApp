package com.llx.common.util

fun String?.isPhone() =
    if (this != null) {
        startsWith("1") && length == 11
    } else false

fun String.isIdCard() =
    """/^[1-9]\d{5}(?:18|19|20)\d{2}(?:0[1-9]|10|11|12)(?:0[1-9]|[1-2]\d|30|31)\d{3}[\dXx]${'$'}/""".toRegex()
        .matches(this)

