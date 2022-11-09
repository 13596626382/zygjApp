package com.tx.zygj.ui.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import com.llx.common.base.BaseActivity
import com.llx.common.custom.ViewPagerAdapter
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.EventBusBean
import com.tx.zygj.bean.TtsBean
import com.tx.zygj.databinding.ActivityMainBinding
import com.tx.zygj.receiver.NetworkConnectChangedReceiver
import com.tx.zygj.socket.WebSocketUtil
import com.tx.zygj.ui.main.fragment.my.MyFragment
import com.tx.zygj.ui.main.fragment.work.WorkFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val workFragment by lazy { WorkFragment() }
    private val myFragment by lazy { MyFragment() }
    private val mDuraction = 2000 // 两次返回键之间的时间差
    var mLastTime: Long = 0 // 最后一次按back键的时刻

    private var networkReceiver: NetworkConnectChangedReceiver? = null
    private lateinit var mTtsSpan: TextToSpeech
    override fun initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        WebSocketUtil.init()
        ttsInit()
        binding.viewPager2.apply {
            currentItem = 0
            adapter = ViewPagerAdapter(this@MainActivity, arrayListOf(workFragment, myFragment))
            isUserInputEnabled = false
            offscreenPageLimit = 1

        }
        binding.work.setOnSingleClickListener {
            if (binding.viewPager2.currentItem == 0) return@setOnSingleClickListener
            binding.viewPager2.setCurrentItem(0, false)
            binding.workImg.setImageResource(R.drawable.icon_work)
            binding.myImg.setImageResource(R.drawable.icon_my_1)
        }
        binding.my.setOnSingleClickListener {
            if (binding.viewPager2.currentItem == 1) return@setOnSingleClickListener
            binding.viewPager2.setCurrentItem(1, false)
            binding.workImg.setImageResource(R.drawable.icon_work_1)
            binding.myImg.setImageResource(R.drawable.icon_my)
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun webSocketEvent(eventBusBean: EventBusBean) {
        if (eventBusBean.type == 1) {
            showConfirmDialog("远程连接失败", "无法打印微信订单小票,请重启App") {
                finish()
            }
        }
        if (eventBusBean.type == 2) {
            showConfirmDialog(content = "远程打印服务已断开") {

            }
        }
        if (eventBusBean.type == 0) {
            workFragment.getStatistics()
            myFragment.getStatistics()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun ttsEvent(ttsBean: TtsBean) {
        speak(ttsBean)
    }

    //不能放在单独类初始化，否则会无法播报
    private fun ttsInit() {
        mTtsSpan = TextToSpeech(topActivity) {
            if (it == TextToSpeech.SUCCESS) {
                val result = mTtsSpan.setLanguage(Locale.CHINESE)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    logE("不支持该语言")
                }
            } else {
                logE("初始化失败")
            }
        }
        mTtsSpan.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String) {
                logE(utteranceId + "播放开始")
            }

            override fun onDone(utteranceId: String) {
                logE(utteranceId + "播放完成")

            }

            override fun onError(utteranceId: String?) {
                logE(utteranceId + "播放失败")
            }
        })
        mTtsSpan.engines.forEach {
            logE(stringBuild(it.name, "    ", it.icon, "   ", it.label))
        }
    }

    //播放语音
    private fun speak(ttsBean: TtsBean) {
        mTtsSpan.speak(
            ttsBean.text,
            TextToSpeech.QUEUE_ADD,
            Bundle(),
            ttsBean.utteranceId
        )

    }

    //停止播放
    private fun stopSpeak() {
        mTtsSpan.stop()
    }

    override fun onResume() {
        super.onResume()
        if (networkReceiver == null) {
            networkReceiver = NetworkConnectChangedReceiver()
        }
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkReceiver)
    }

    override fun onBackPressed() {
        if ((System.currentTimeMillis() - mLastTime) > mDuraction) {
            toast("再按一次退出")
            mLastTime = System.currentTimeMillis()
        } else {
            WebSocketUtil.disconnect(1000, "用户退出App")
            WebSocketUtil.closeApp()
            stopSpeak()
            EventBus.getDefault().unregister(this)
            finish()
        }
    }


}