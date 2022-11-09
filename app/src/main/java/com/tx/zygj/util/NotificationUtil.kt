package com.tx.zygj.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.tx.zygj.R

object NotificationUtil {
    private lateinit var mManager: NotificationManager
//    private lateinit var mBuilder: NotificationCompat.Builder
    fun init(context: Context) {
        mManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 适配8.0及以上 创建渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("渠道id", "渠道名称", NotificationManager.IMPORTANCE_HIGH).apply {
                    description = "描述"
                    setShowBadge(true) // 是否在桌面显示角标
                }
            mManager.createNotificationChannel(channel)
        }

    }

    /**
     * 发送通知
     * @param title 标题
     * @param content 文本内容
     */
    fun notify(context: Context, title: String, content: String) {
        // 点击意图 // setDeleteIntent 移除意图
//        val intent = Intent(mContext, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        // 构建配置
        val mBuilder = NotificationCompat.Builder(context, "渠道id")
            .setContentTitle(title) // 标题
            .setContentText(content) // 文本
            .setSmallIcon(R.mipmap.ic_launcher) // 小图标
//            .setLargeIcon(BitmapFactory.decodeResource(, R.mipmap.ic_launcher)) // 大图标
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 7.0 设置优先级
//            .setContentIntent(pendingIntent) // 跳转配置
            .setAutoCancel(true) // 是否自动消失（点击）or mManager.cancel(mNormalNotificationId)、cancelAll、setTimeoutAfter()
        // 发起通知
        mManager.notify(9001, mBuilder.build())
    }
}