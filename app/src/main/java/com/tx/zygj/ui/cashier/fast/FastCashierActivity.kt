package com.tx.zygj.ui.cashier.fast

import android.content.Intent
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.showLoadingDialog
import com.llx.common.util.stringBuild
import com.llx.common.util.toast
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.bean.TtsBean
import com.tx.zygj.databinding.ActivityFastCashierBinding
import com.tx.zygj.ui.scan.ScanCodeActivity
import com.tx.zygj.util.SunmiPrintHelper
import org.greenrobot.eventbus.EventBus


/**
 * 快速收银
 */
class FastCashierActivity :
    BaseActivity<ActivityFastCashierBinding>(R.layout.activity_fast_cashier) {
    private val model: FastCashierViewModel by viewModels()
    private lateinit var loadDialog: LoadingPopupView

    private var keyboardNumber = ""
    private var price = ""
    private var orderNo = ""
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.settle.setOnSingleClickListener {
            if (keyboardNumber == "" || keyboardNumber.toDouble() <= 0.00) {
                toast("请输入正确的加油金额")
                return@setOnSingleClickListener
            }
            if (orderNo != "" && price == keyboardNumber) {
                model.orderNo.value = orderNo
            } else {
                price = keyboardNumber
                model.getFastOrder(price.toDouble())
            }
        }
        model.orderNo.observe(this) {
            orderNo = it
            startActivityForResult(
                Intent(mContext, ScanCodeActivity::class.java).putExtra(
                    CommonConstant.SCAN_TYPE, 0
                ), CommonConstant.REQUEST_CODE
            )
        }
        model.paySuccessBean.observe(this) {
            loadDialog.dismiss()
            EventBus.getDefault().post(TtsBean(stringBuild("收款", it.actual, "元欢迎下次光临"), it.orderNo))
            SunmiPrintHelper.sendFastCashierRawData(it)
        }
        keyboardView()
        binding.hundred.setOnSingleClickListener {
            keyboardNumber = "100"
            binding.money.text = "100"
        }
        binding.twoHundred.setOnSingleClickListener {
            keyboardNumber = "200"
            binding.money.text = "200"
        }
        binding.threeHundred.setOnSingleClickListener {
            keyboardNumber = "300"
            binding.money.text = "300"
        }
        binding.fiveHundred.setOnSingleClickListener {
            keyboardNumber = "500"
            binding.money.text = "500"
        }

        model.requestResult.observe(this) {
            loadDialog.dismiss()
        }
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
            binding.numberSpot
        )

        numberArray.forEach {
            it.setOnClickListener { _ ->
                val number = it.text.toString()
                //判断第一个输入的是“.”
                if (number == "." && keyboardNumber.isEmpty()) {
                    return@setOnClickListener
                }
                //判断字符串中是否包含”.“
                if (number == "." && keyboardNumber.contains(".")) {
                    return@setOnClickListener
                }
                //前两位是否出现两个0
                if (number == "0" && keyboardNumber == "0") {
                    return@setOnClickListener
                }
                /**
                 * 小数点后两位不做记录
                 * number.indexOf(".") + 1
                 * 找到小数点出现的位置并 + 1
                 * number.length - (number.indexOf(".") + 1) == 2
                 * 用总长度 - 小数点出现的位置 = 2 证明小数点已经两位
                 */
                if (keyboardNumber.contains(".") && keyboardNumber.length - (keyboardNumber.indexOf(
                        "."
                    ) + 1) == 2
                ) {
                    return@setOnClickListener
                }
                //第一位为0第二位不是点替换第一位
                if (keyboardNumber == "0" && number != ".") {
                    keyboardNumber = number
                } else {
                    keyboardNumber += number
                }
                binding.money.text = keyboardNumber

            }
        }
        binding.delete.setOnClickListener {
            keyboardNumber = if (keyboardNumber.isEmpty()) "" else keyboardNumber.substring(
                0,
                keyboardNumber.length - 1
            )
            binding.money.text = keyboardNumber.ifEmpty { "" }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CommonConstant.REQUEST_CODE) {
            val code = data?.getStringExtra(CommonConstant.SCAN_CODE)
            if (code != null) {
                loadDialog = showLoadingDialog("查询支付结果")
                loadDialog.show()
                model.fastPay(code, orderNo, keyboardNumber.toDouble())
            }
        }
    }
}