package com.llx.common.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.llx.common.CommonConstant
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView

fun View.setOnBackActivity(activity: AppCompatActivity) {
    this.setOnSingleClickListener {
        activity.onBack()
    }
}
//禁止连续点击
inline fun View.setOnSingleClickListener(crossinline block: (View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        if (System.currentTimeMillis() - lastClickTime < CommonConstant.FAST_CLICK_DELAY_TIME) {
            return@setOnClickListener
        }
        lastClickTime = System.currentTimeMillis()
        block.invoke(it)
    }
}
//只返回输入结果
inline fun EditText.addAfterTextChanged(crossinline block: String.() -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            block.invoke(s.toString())
        }

    })
}


fun TextView.textString() = text.toString().trim()

fun Context.showPopupView(view: View, popupView: BasePopupView) =
    showPopupView(view, popupView, hasShadowBg = true, dismissOnTouchOutside = true)


fun Context.showPopupView(view: View, popupView: BasePopupView, hasShadowBg: Boolean) =
    showPopupView(view, popupView, hasShadowBg, true)


fun Context.showPopupView(
    view: View,
    popupView: BasePopupView,
    hasShadowBg: Boolean,
    dismissOnTouchOutside: Boolean
) {
    XPopup.Builder(this)
        .hasShadowBg(hasShadowBg)
        .dismissOnTouchOutside(dismissOnTouchOutside)
        .atView(view)
        .asCustom(popupView)
        .show()
}