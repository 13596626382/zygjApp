package com.llx.common.util

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.llx.common.CommonConstant
import com.llx.common.dialog.StringDialogFragment
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.lxj.xpopupext.listener.TimePickerListener
import com.lxj.xpopupext.popup.TimePickerPopup
import java.util.*

/**
 * 加载框
 * @param content 内容
 */
fun Context.showLoadingDialog(content: String) =
    XPopup.Builder(this)
        .dismissOnBackPressed(false)
        .isLightNavigationBar(true)
        .asLoading(content, LoadingPopupView.Style.ProgressBar) as LoadingPopupView

/**
 * 提示框Dialog
 * @param title 标题
 * @param content 内容
 */
inline fun View.showConfirmDialog(
    title: String = "提示",
    content: String,
    cancelBenText: String = "取消",
    confirmBtnText: String = "确定",
    crossinline block: () -> Unit
) {
    setOnSingleClickListener {
        XPopup.Builder(context)
            .isDestroyOnDismiss(true)
            .asConfirm(
                title, content,
                cancelBenText, confirmBtnText, {
                    block.invoke()
                }, null, false
            )
            .show()
    }
}


/**
 * 提示框Dialog
 * @param title 标题
 * @param content 内容
 */
inline fun showConfirmDialog(
    title: String = "提示",
    content: String,
    cancelBenText: String = "取消",
    confirmBtnText: String = "确定",
    crossinline block: () -> Unit
) {
    XPopup.Builder(topActivity.activity)
        .isDestroyOnDismiss(true)
        .asConfirm(
            title, content,
            cancelBenText, confirmBtnText, {
                block.invoke()
            }, null, false
        )
        .show()
}

/**
 * 选择内容Dialog
 * @param data 内容数据
 */
inline fun View.showDialog(
    data: ArrayList<String>,
    activity: AppCompatActivity,
    crossinline block: (String, Int) -> Unit
) {
    setOnSingleClickListener {
        val dialogFragment = StringDialogFragment()
        dialogFragment.withArguments(CommonConstant.STRING_DIALOG_DATA to data)
        dialogFragment.onSelectStringResult = { string, position ->
            block.invoke(string, position)
        }
        dialogFragment.show(activity.supportFragmentManager, CommonConstant.STRING_DIALOG_FRAGMENT)
    }
}


/**
 * 选择时间的Dialog
 */
inline fun View.showDateDialog(
    isYM: Boolean = false,
    crossinline onSelectResult: (Date) -> Unit
) {
    setOnSingleClickListener {
        val startDate: Calendar = Calendar.getInstance()
        startDate.set(2022, 1, 1)
        val endDate: Calendar = Calendar.getInstance()
        endDate.set(2050, 1, 1)
        val popup =
            TimePickerPopup(context)
                .setMode(if (isYM) TimePickerPopup.Mode.YM else TimePickerPopup.Mode.YMD)
                .setDateRange(startDate, endDate) //设置日期范围
                .setTimePickerListener(object : TimePickerListener {
                    override fun onTimeChanged(date: Date?) {
                        //时间改变
                    }

                    override fun onTimeConfirm(date: Date, view: View?) {
                        //点击确认时间
                        onSelectResult.invoke(date)
                    }

                    override fun onCancel() {}
                })

        XPopup.Builder(context)
            .asCustom(popup)
            .show()
    }
}


