package com.tx.zygj.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.llx.common.util.isNetworkAvailable
import com.tx.zygj.socket.WebSocketUtil

//网路状态监听
class NetworkConnectChangedReceiver : BroadcastReceiver() {
    private var isFirst = true //是否第一次通知
    override fun onReceive(context: Context?, intent: Intent?) {
        if (!isFirst && isNetworkAvailable) {
            WebSocketUtil.netWorkReconnect()
        }
        isFirst = false
    }
}