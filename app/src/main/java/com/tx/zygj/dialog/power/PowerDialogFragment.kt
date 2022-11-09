package com.tx.zygj.dialog.power

import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.llx.common.base.BaseDialogFragment
import com.llx.common.util.showLoadingDialog
import com.llx.common.util.toast
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.databinding.DialogPowerPassBinding


class PowerDialogFragment : BaseDialogFragment<DialogPowerPassBinding>(R.layout.dialog_power_pass) {
    private val model: PowerDialogViewModel by viewModels()
    private lateinit var popUpView: LoadingPopupView
    var onPassWordCorrect: (() -> Unit)? = null
    var number = ""
    override fun init() {
        popUpView = mContext.showLoadingDialog("校验密码中")
        val animation: Animation = AnimationUtils.loadAnimation(mContext, R.anim.shake)
        binding.dismiss.setOnClickListener {
            dismiss()
        }
        model.requestResult.observe(this) {
            popUpView.dismiss()
            if (it) {
                dismiss()
                onPassWordCorrect?.invoke()
            } else {
                binding.textView64.visibility = View.VISIBLE
                binding.textView64.text = "密码错误"
                binding.textView64.startAnimation(animation)
                number = ""
                binding.pw1.text = ""
                binding.pw2.text = ""
                binding.pw3.text = ""
                binding.pw4.text = ""
                binding.pw5.text = ""
                binding.pw6.text = ""

            }
        }
        model.exception.observe(this) {
            toast("出错啦")
            popUpView.dismiss()
        }
        keyboardView()
    }

    private fun keyboardView() {
        val numberArray = arrayOf(
            binding.number0,
            binding.number1,
            binding.number2,
            binding.number3,
            binding.number4,
            binding.number5,
            binding.number6,
            binding.number7,
            binding.number8,
            binding.number9,
        )
        numberArray.forEach {
            it.setOnClickListener { _ ->
                number += it.text.toString()
                if (number.length > 6) {
                    number = number.substring(0, number.length - 1)
                    return@setOnClickListener
                }
                when (number.length) {
                    1 -> binding.pw1.text = it.text.toString()
                    2 -> binding.pw2.text = it.text.toString()
                    3 -> binding.pw3.text = it.text.toString()
                    4 -> binding.pw4.text = it.text.toString()
                    5 -> binding.pw5.text = it.text.toString()
                    6 -> binding.pw6.text = it.text.toString()
                }
                if (number.length == 6) {
                    popUpView.show()
                    model.handover(number)
                }
            }
        }
        binding.backspace.setOnClickListener {
            if (number == "") {
                return@setOnClickListener
            }
            number = number.substring(0, number.length - 1)
            when (number.length) {
                0 -> binding.pw1.text = ""
                1 -> binding.pw2.text = ""
                2 -> binding.pw3.text = ""
                3 -> binding.pw4.text = ""
                4 -> binding.pw5.text = ""
                5 -> binding.pw6.text = ""
            }
        }
    }

    override fun getDialogGravity() = Gravity.BOTTOM

    override fun getCanceled() = false

}