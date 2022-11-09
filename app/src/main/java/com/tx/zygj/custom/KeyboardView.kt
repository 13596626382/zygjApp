package com.tx.zygj.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import com.tx.zygj.R
import com.tx.zygj.databinding.ViewKeyboardBinding

class KeyboardView(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val binding: ViewKeyboardBinding
    lateinit var onNumberClickListener: (String)-> Unit
    lateinit var onDeleteClickListener: (String)-> Unit
    private var number = "应收款：￥"
    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_keyboard,
            null,
            false
        )
        binding.root.updateLayoutParams {
            width = LayoutParams.MATCH_PARENT
        }
        addView(binding.root)
        keyboardView()
    }

    private fun keyboardView() {
        val delete = binding.delete
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
            binding.numberSpot,
        )
        numberArray.forEach {
            it.setOnClickListener { _ ->
                if (it.text.toString() == "." && number.contains(".")) {
                    return@setOnClickListener
                }
                if (it.text.toString() == "." && number.length < 6) {
                    return@setOnClickListener
                }
                number += it.text.toString()
                onDeleteClickListener.invoke(number)
                onNumberClickListener.invoke(number)

            }
        }
        delete.setOnClickListener {
            if (number.length <= 5) {
                return@setOnClickListener
            }
            number = number.substring(0, number.length - 1)
            onDeleteClickListener.invoke(number)
                                    //            binding.money.text = A

        }
    }
}