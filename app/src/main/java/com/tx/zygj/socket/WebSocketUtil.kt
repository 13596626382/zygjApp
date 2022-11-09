package com.tx.zygj.socket

import com.llx.common.CommonConstant
import com.llx.common.util.*
import com.tx.zygj.bean.EventBusBean
import com.tx.zygj.bean.SocKetBean
import com.tx.zygj.bean.TtsBean
import com.tx.zygj.util.SunmiPrintHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

object WebSocketUtil {
    private const val MAX_NUM = 5 //最大重连次数


    private var mWbSocketUrl: String = ""
    private var mClient: OkHttpClient? = null
    private var mWebSocket: WebSocket? = null
    private var mRequest: Request? = null
    private var isConnect = false

    private var reconnect_num = 0 //重连次数
    private var isReconnect = true //是否重连

    //初始化
    fun init() {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor {
                logE("socket---->", it)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        mWbSocketUrl = "wss://zygj.cczxyl.com/websocket/${CommonConstant.getUserInfo()?.gasId}"
//        mWbSocketUrl = "ws://192.168.1.114:9601/websocket/${CommonConstant.getUserInfo()?.gasId}"
        mClient = OkHttpClient.Builder()
            .pingInterval(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
        mRequest = Request.Builder()
            .url(mWbSocketUrl)
            .build()
        connect()
    }

    //连接
    private fun connect() {
        if (!isConnect()) {
            mClient?.newWebSocket(mRequest!!, WebListener())
        }
    }

    //尝试重连
    fun reconnect() {
        if (!isReconnect) return
        if (reconnect_num < MAX_NUM) {
            logE("尝试第${reconnect_num + 1} 次重连")
            Thread.sleep(10000)
            connect()
            reconnect_num++
        } else {
            "重连失败".runOnUiThread()
            EventBus.getDefault().post(EventBusBean(1))
        }
    }

    //尝试重连
    fun netWorkReconnect() {
        if (!isConnect()) {
            logE("断网重连")
            connect()
        }
    }

    //是否连接
    private fun isConnect() = mWebSocket != null && isConnect

    class WebListener : WebSocketListener() {
        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            mWebSocket = null
            isConnect = false
            logE("socket---->", "关闭 $reason")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            mWebSocket = null
            isConnect = false
            logE("socket---->", "关闭 $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            logE("socket---->", "连接失败 $t ${t.message}")
            mWebSocket = null
            isConnect = false
            if (!isNetworkAvailable) {
                "网络连接中断".runOnUiThread()
                return
            }
            if (response?.code == 404) {
                EventBus.getDefault().post(EventBusBean(2))
                return
            }
            reconnect()
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            logE("socket---->", "收到消息bytes $bytes")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            logE("socket---->", "收到消息 $text")
            if (text.startsWith("{") && text.endsWith("}")) {
                val socKetBean = text.fromJson<SocKetBean>()
                if (!CommonConstant.isOrder(socKetBean.data.orderNo)) {
                    CommonConstant.setOrderNo(socKetBean.data.orderNo)
                    SunmiPrintHelper.sendWXCashierRawData(socKetBean.data)

                    EventBus.getDefault().post(EventBusBean(0))
                    EventBus.getDefault().post(
                        TtsBean(
                            socKetBean.data.memberName + "支付" + socKetBean.data.actual?.subPoint() + "元, 欢迎下次光临",
                            socKetBean.data.orderNo
                        )
                    )
                }
            }


        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            logE("socket---->", "连接成功")
            mWebSocket = webSocket
            isConnect = response.code == 101
            if (!isConnect) {
                reconnect()
            }
            reconnect_num = 0
        }
    }

    //发送String消息
    fun send(message: String) {
        mWebSocket?.send(message)
    }

    //断开连接
    fun disconnect(code: Int, reason: String) {
        mWebSocket?.close(code, reason)
    }

    //是否关闭重连
    fun isCloseReconnect(isReconnect: Boolean) {
        this.isReconnect = isReconnect
    }

    //退出app
    fun closeApp() {
        mClient = null
        mWebSocket = null
        mRequest = null
        isConnect = false
    }
}