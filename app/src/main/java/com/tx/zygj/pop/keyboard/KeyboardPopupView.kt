package com.tx.zygj.pop.keyboard

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.lxj.xpopup.impl.PartShadowPopupView
import com.tx.zygj.R

class KeyboardPopupView(context: Context) : PartShadowPopupView(context) {
    lateinit var onNumberListener: (String) -> Unit
    lateinit var onDeleteListener: (String) -> Unit
    private var number = "应收款：￥"
    override fun getImplLayoutId() = R.layout.view_keyboard
    override fun onCreate() {
        val number0 = findViewById<TextView>(R.id.number_0)
        val number1 = findViewById<TextView>(R.id.number_1)
        val number2 = findViewById<TextView>(R.id.number_2)
        val number3 = findViewById<TextView>(R.id.number_3)
        val number4 = findViewById<TextView>(R.id.number_4)
        val number5 = findViewById<TextView>(R.id.number_5)
        val number6 = findViewById<TextView>(R.id.number_6)
        val number7 = findViewById<TextView>(R.id.number_7)
        val number8 = findViewById<TextView>(R.id.number_8)
        val number9 = findViewById<TextView>(R.id.number_9)
        val numberSpot = findViewById<TextView>(R.id.number_spot)
        val delete = findViewById<ImageView>(R.id.delete)
        val numberArray = arrayOf(
            number0,
            number1,
            number2,
            number3,
            number4,
            number5,
            number6,
            number7,
            number8,
            number9,
            numberSpot,
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
                onNumberListener.invoke(number)
            }
        }
        delete.setOnClickListener {
            if (number.length <= 5) {
                return@setOnClickListener
            }
            number = number.substring(0, number.length - 1)
            onDeleteListener.invoke(number)

        }
    }
}