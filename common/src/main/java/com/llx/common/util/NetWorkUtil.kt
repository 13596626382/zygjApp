package com.llx.common.util

import android.content.Context
import android.net.ConnectivityManager

//检查网络连接
val isNetworkAvailable: Boolean
    get() {
        val conn = (topActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val netWork = conn.getNetworkCapabilities(conn.activeNetwork)
        return netWork != null
    }