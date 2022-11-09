package com.tx.zygj.ui.voice

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.view.View
import com.llx.common.base.BaseActivity
import com.llx.common.util.appPageNameList
import com.llx.common.util.showConfirmDialog
import com.llx.common.util.toast
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityVoicePageBinding
import java.io.File

//语音包
class VoicePageActivity : BaseActivity<ActivityVoicePageBinding>(R.layout.activity_voice_page) {
    override fun initData() {
        binding.titleBarView.setOnBack(this)
        if (appPageNameList.filter { it.packageName == "com.google.android.tts" }.size == 1) {
            binding.downText.text = "已安装语音包"
            binding.downImg.visibility = View.GONE
            binding.down.isEnabled = false
        }
        binding.down.showConfirmDialog(content = "是否下载语音包") {
            startDownLoad()
        }
    }


    private fun startDownLoad() {

        //下载链接
        val downloadUrl =
            "http://down-yxlink.mdpda.net/azrj/Googlewenzizhuanyuyinyinqin_yxdown.com.apk"

        val fileName = downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1)
        //这里下载到指定的目录，我们存在公共目录下的download文件夹下
        val fileUri = Uri.fromFile(
            File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                System.currentTimeMillis().toString() + "-" + fileName
            )
        )
        //开始构建 DownloadRequest 对象
        val request = DownloadManager.Request(Uri.parse(downloadUrl))

        //构建通知栏样式
        request.setTitle("下载Google语音包")
        request.setDescription("语音包下载中")

        //下载或下载完成的时候显示通知栏
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE or DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        //指定下载的文件类型为APK
        request.setMimeType("application/vnd.android.package-archive")
//            request.addRequestHeader()   //还能加入请求头
//            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)   //能指定下载的网络

        //指定下载到本地的路径(可以指定URI)
        request.setDestinationUri(fileUri)

        //开始构建 DownloadManager 对象
        val downloadManager = mContext.getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        //加入Request到系统下载队列，在条件满足时会自动开始下载。返回的为下载任务的唯一ID
        val requestID = downloadManager.enqueue(request)
        toast("开始下载")
        //注册下载任务完成的监听
        mContext.registerReceiver(object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {

                //已经完成
                if (intent.action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                    //获取下载ID
                    val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    val uri = downloadManager.getUriForDownloadedFile(id)
                    toast("安裝完成请重启app")
                    installApk(uri)

                } else if (intent.action.equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
                    toast("下载失败")
                }

            }
        }, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private fun installApk(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        startActivity(intent)
    }

}