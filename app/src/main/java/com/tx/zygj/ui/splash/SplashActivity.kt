package com.tx.zygj.ui.splash

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityLaunchBinding
import com.tx.zygj.ui.login.LoginActivity
import com.tx.zygj.ui.main.MainActivity
import com.tx.zygj.util.NotificationUtil
import com.tx.zygj.util.OcrUtil
import com.tx.zygj.util.SunmiPrintHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//闪屏页
class SplashActivity : BaseActivity<ActivityLaunchBinding>(R.layout.activity_launch) {
    override fun initData() {
        load()
        binding.retry.setOnClickListener {
            binding.retry.visibility = View.INVISIBLE
            load()
        }
    }

    private fun load() {
        lifecycleScope.launch {
            if (isNetworkAvailable) {
                delay(1000)
                binding.retry.visibility = View.INVISIBLE
                OcrUtil.init(this@SplashActivity)
                NotificationUtil.init(this@SplashActivity)
                SunmiPrintHelper.init(this@SplashActivity)

                val userInfo = readContent(DatastoreUtil.USER_INFO)
                val printBean = readContent(DatastoreUtil.PRINT_SETTING)
                if (userInfo != "") {
                    CommonConstant.setUserInfo(userInfo.fromJson())
                    logE(userInfo)
                    toast("上次交班时间为: ${CommonConstant.getUserInfo()!!.startTime.toYMDHMSDate()}")
                    if (printBean != "") {
                        CommonConstant.setPrintBean(printBean.fromJson())
                        logE(printBean)
                    }
                    startActivity<MainActivity>()
                } else {
                    startActivity<LoginActivity>()
                }

                onBackActivity()

            } else {
                toast("无网络连接请检查网络连接状态")
                binding.retry.visibility = View.VISIBLE
            }

        }
    }
}