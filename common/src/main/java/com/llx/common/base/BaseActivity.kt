package com.llx.common.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.llx.common.util.activityCache
import com.lxj.xpopup.util.KeyboardUtils
import com.zackratos.ultimatebarx.ultimatebarx.statusBarOnly

abstract class BaseActivity<DB : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
    AppCompatActivity() {
    lateinit var binding: DB
    lateinit var mContext: Context
    private var isFirst = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        statusBarOnly {
            // 布局是否侵入状态栏（true 不侵入，false 侵入）
            fitWindow = getFitWindow()
            // 状态栏背景颜色（资源 id）
            colorRes = getColorRes()
            // light模式：状态栏字体 true: 灰色，false: 白色 Android 6.0+
            light = getLight()
        }
        activityCache.add(this)
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(getBackEnabled()) {
            override fun handleOnBackPressed() {
                onBack()
            }
        })
        mContext = this
        initData()
    }

    open fun getFitWindow() = false

    open fun getColorRes() = Color.TRANSPARENT

    open fun getLight() = true

    //是否拦截返回事件
    open fun getBackEnabled() = false

    open fun onBack() {
    }

    abstract fun initData()

    override fun onResume() {
        super.onResume()
        if (!isFirst) {
            update()
        }
        isFirst = false
    }

    open fun update() {

    }

    /**
     * 点击空白处关闭软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            if (isShouldHideInput(currentFocus, ev)) {
                KeyboardUtils.hideSoftInput(currentFocus)
                return super.dispatchTouchEvent(ev)
            }
        }
        return super.dispatchTouchEvent(ev)

    }

    /**
     * 判断是否点击键盘区域外
     */
    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false
    }


}