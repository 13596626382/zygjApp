package com.llx.common.util

import android.util.Log

fun logE(tag: String, msg: String) = Log.e(tag, msg)

fun logE(msg: String) = Log.e(topActivity.localClassName, msg)