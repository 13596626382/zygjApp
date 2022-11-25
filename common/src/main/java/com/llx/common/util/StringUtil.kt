package com.llx.common.util


//截取小数点后两位,如果是00返回截取后的字符串，不是返回原字符串
fun String.subPoint() =
    if (substring(indexOf("."), length) == ".00" && substring(indexOf("."), length) == ".0") {
        substring(0, indexOf("."))
    } else this
