package com.llx.common.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.app.toast.ToastX
import com.app.toast.snackbar.SnackBarX
import com.llx.common.R

fun toast(message: String) =
    topActivity.toast(message)

fun Fragment.toast(message: String) =
    requireActivity().toast(message)

fun Context.toast(message: String) {
    ToastX.with(this as Activity).fixBadTokenException().apply {
        text(message)
        backgroundColor(R.color.color_5F5F5F)
        offset(20.dp.toInt())
        show()
    }
}

fun SnackBarX.fixBadTokenException(): SnackBarX = apply {
    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
        try {
            @SuppressLint("DiscouragedPrivateApi")
            val tnField = SnackBarX::class.java.getDeclaredField("mTN")
            tnField.isAccessible = true
            val tn = tnField.get(this)

            val handlerField = tnField.type.getDeclaredField("mHandler")
            handlerField.isAccessible = true
            val handler = handlerField.get(tn) as Handler

            val looper = checkNotNull(Looper.myLooper()) {
                "Can't toast on a thread that has not called Looper.prepare()"
            }
            handlerField.set(tn, object : Handler(looper) {
                override fun handleMessage(msg: Message) {
                    try {
                        handler.handleMessage(msg)
                    } catch (ignored: WindowManager.BadTokenException) {
                    }
                }
            })
        } catch (ignored: IllegalAccessException) {
        } catch (ignored: NoSuchFieldException) {
        }
    }
}