package com.tx.zygj.util

import android.content.Context
import com.llx.common.CommonConstant
import com.llx.common.bean.PrintBean
import com.llx.common.util.*
import com.sunmi.peripheral.printer.InnerPrinterCallback
import com.sunmi.peripheral.printer.InnerPrinterManager
import com.sunmi.peripheral.printer.InnerResultCallback
import com.sunmi.peripheral.printer.SunmiPrinterService
import com.tx.zygj.bean.HandoverBean
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.bean.PaySuccessBean

object SunmiPrintHelper {

    private var sunmiPrinterService: SunmiPrinterService? = null

    private var innerPrinterCallback: InnerPrinterCallback? = object : InnerPrinterCallback() {
        override fun onConnected(service: SunmiPrinterService) {
            sunmiPrinterService = service
        }

        override fun onDisconnected() {
            sunmiPrinterService = null
        }
    }

    fun init(context: Context) {
        InnerPrinterManager.getInstance().bindService(
            context, innerPrinterCallback
        )
    }

    fun printText() {
        sunmiPrinterService?.printText("1231231", object : InnerResultCallback() {
            override fun onRunResult(isSuccess: Boolean) {
                logE("打印结果 $isSuccess")
            }

            override fun onReturnString(result: String?) {
                logE("打印结果 $result")
            }

            override fun onRaiseException(code: Int, msg: String?) {
                logE("失败了大兄弟 $msg")
            }

            override fun onPrintResult(code: Int, msg: String?) {
                logE("真是打印结果 $msg")
            }

        })
    }

    fun finish(context: Context) {
        InnerPrinterManager.getInstance().unBindService(
            context, innerPrinterCallback
        )
    }

    //测试打印
    fun testPrint(printBean: PrintBean?) {
        sunmiPrinterService!!.apply {
//            val bitmap = getBitmap(printBean?.logo!!)
//            printBitmap(bitmap, null)
            if (printBean?.shopStatus == 1) {
                setting(
                    "${CommonConstant.getUserInfo()?.gasName}",
                    1,
                    line = if (printBean.typeStatus == 0 && printBean.titleStatus == 0) 2 else 1
                )
            }
            if (printBean?.titleStatus == 1) {
                setting(
                    printBean.titleContent,
                    alignment = 1,
                    line = if (printBean.typeStatus == 0) 2 else 1
                )
            }

            if (printBean?.typeStatus == 1) {
                setting("加油小票", alignment = 1, line = 2)
            }


            if (printBean?.orderNoStatus == 1) {
                setting("订单编号: 12312312312312")
            }
            if (printBean?.operationStatus == 1) {
                setting("交易时间: 2022-10-10 2:40")
            }
            setting("----------------------------------")


            setting("操作员： XXX")
            setting("枪号： #号枪")
            setting(
                "型号： 油"
            )
            setting("单价： 123")
            setting("数量： 10L")
            setting("应收金额： 1230")
            setting("优惠金额： 0")

            setting("----------------------------------")

            setting("实收金额： 1230", size = 30f)

            setting("支付方式： 我家开的银行")
//            setting("加油赠送： +0.00")
            setting("赠送积分： 0")

            setting("----------------------------------")

            if (printBean?.memberNameStatus == 1) {
                setting("会 员 名： 无敌")
            }
            if (printBean?.phoneStatus == 1) {
                setting("会员卡号： 1234564874321")
            }
            if (printBean?.memberTypeStatus == 1) {
                setting(
                    "会员级别： 至尊无上的级别"
                )
            }
            if (printBean?.balanceStatus == 1) {
                setting("账户余额： 100个W")
            }
            if (printBean?.integralStatus == 1) {
                setting("账户积分： 3000")
            }
            setting("----------------------------------")

            if (printBean?.addressStatus == 1) {
                setting(
                    "地址： ${CommonConstant.getUserInfo()?.address}",
                    line = if (printBean.contactStatus == 0) 2 else 1
                )
            }
            if (printBean?.contactStatus == 1) {
                setting(
                    "电话： ${CommonConstant.getUserInfo()?.gasPhone}",
                    line = if (printBean.footnoteStatus == 0) 3 else 2
                )
            }

            if (printBean?.footnoteStatus == 1) {
                setting(printBean.footnote, alignment = 1, line = 4)
            }

        }
    }

    //收银打印小票
    fun sendCashierRawData(
        paySuccessBean: PaySuccessBean?, memberManageBean: MemberManageBean?,
        printBean: PrintBean? = CommonConstant.getPrintBean()
    ) {
        if (printBean == null) {
            toast("请重新获取打印设置信息")
            return
        }
        sunmiPrinterService!!.apply {
            if (printBean.shopStatus == 1) {
                setting(
                    "${CommonConstant.getUserInfo()?.gasName}",
                    1,
                    line = if (printBean.typeStatus == 0 && printBean.titleStatus == 0) 2 else 1
                )
            }
            if (printBean.titleStatus == 1) {
                setting(
                    printBean.titleContent,
                    alignment = 1,
                    line = if (printBean.typeStatus == 0) 2 else 1
                )
            }
            if (printBean.typeStatus == 1) {
                setting("加油小票", alignment = 1, line = 2)
            }
            if (printBean.orderNoStatus == 1) {
                setting("订单编号: ${paySuccessBean?.orderNo}")
            }
            if (printBean.operationStatus == 1) {
                setting("交易时间: ${paySuccessBean?.payDate}")
            }
            setting("----------------------------------")

            setting("加油员：${paySuccessBean?.gasMan}")
            setting("枪号：${paySuccessBean?.gunNumber} 号枪")
            setting(
                "型号：${paySuccessBean?.model}${
                    when (paySuccessBean?.typeId) {
                        1 -> "汽油"
                        2 -> "柴油"
                        else -> "天然气"
                    }
                }"
            )
            setting("单价：${paySuccessBean?.oilPrice}")
            setting("数量：${paySuccessBean?.oilsRise}L")
            setting("应收金额：${paySuccessBean?.totalPrice}")
            setting("优惠金额：${paySuccessBean?.discount}")

            setting("----------------------------------")

            setting("实收金额：${paySuccessBean?.actual}", size = 30f)
            setting("支付方式：${paySuccessBean?.payModel}")
            setting("赠送积分：${paySuccessBean?.integral}")

            setting("----------------------------------")

            if (memberManageBean != null) {
                if (printBean.memberNameStatus == 1) {
                    setting("会 员 名：${memberManageBean.nickName}")
                }
                if (printBean.phoneStatus == 1) {
                    setting("会员卡号：${memberManageBean.phone}")
                }
                if (printBean.memberTypeStatus == 1) {
                    setting(
                        "会员级别：${
                            when (memberManageBean.memberType) {
                                1 -> "白银"
                                2 -> "黄金"
                                3 -> "白金"
                                4 -> "至尊"
                                else -> "神秘会员"
                            }
                        }"
                    )
                }
                if (printBean.balanceStatus == 1) {
                    if (paySuccessBean?.payModel != "扫码支付") {
                        setting("账户余额：${paySuccessBean?.balance}")
                    }
                }
                if (printBean.integralStatus == 1) {
                    setting("账户积分：0")
                }
                setting("----------------------------------")
            }

            if (printBean.addressStatus == 1) {
                setting(
                    "地址：${CommonConstant.getUserInfo()?.address}",
                    line = if (printBean.contactStatus == 0) 2 else 1
                )
            }
            if (printBean.contactStatus == 1) {
                setting(
                    "电话：${CommonConstant.getUserInfo()?.gasPhone}",
                    line = if (printBean.footnoteStatus == 0) 3 else 2
                )
            }

            if (printBean.footnoteStatus == 1) {
                setting(printBean.footnote, alignment = 1, line = 4)
            }
        }
    }

    //快速收银打印小票
    fun sendFastCashierRawData(
        paySuccessBean: PaySuccessBean?,
        printBean: PrintBean? = CommonConstant.getPrintBean()
    ) {
        if (printBean == null) {
            toast("请重新获取打印设置信息")
            return
        }
        sunmiPrinterService!!.apply {
            if (printBean.shopStatus == 1) {
                setting(
                    "${CommonConstant.getUserInfo()?.gasName}",
                    1,
                    line = if (printBean.typeStatus == 0 && printBean.titleStatus == 0) 2 else 1
                )
            }
            if (printBean.titleStatus == 1) {
                setting(
                    printBean.titleContent,
                    alignment = 1,
                    line = if (printBean.typeStatus == 0) 2 else 1
                )
            }

            if (printBean.typeStatus == 1) {
                setting("快速收银小票", alignment = 1, line = 2)
            }

            if (printBean.orderNoStatus == 1) {
                setting("订单编号: ${paySuccessBean?.orderNo}")
            }
            if (printBean.operationStatus == 1) {
                setting("交易时间: ${paySuccessBean?.payDate}")
            }
            setting("实收金额: ${paySuccessBean?.actual}", size = 30f)
            setting("----------------------------------")

            if (printBean.addressStatus == 1) {
                setting(
                    "地址：${CommonConstant.getUserInfo()?.address}",
                    line = if (printBean.contactStatus == 0) 2 else 1
                )
            }
            if (printBean.contactStatus == 1) {
                setting(
                    "电话：${CommonConstant.getUserInfo()?.gasPhone}",
                    line = if (printBean.footnoteStatus == 0) 3 else 2
                )
            }
            if (printBean.footnoteStatus == 1) {
                setting("欢迎再次光临", alignment = 1, line = 4)
            }

        }
    }


    //钱包充值打印小票
    fun sendWalletRawData(
        paySuccessBean: PaySuccessBean?,
        memberManageBean: MemberManageBean?,
        printBean: PrintBean? = CommonConstant.getPrintBean()
    ) {
        if (printBean == null) {
            toast("请重新获取打印设置信息")
            return
        }
        sunmiPrinterService!!.apply {
            if (printBean.shopStatus == 1) {
                setting(
                    "${CommonConstant.getUserInfo()?.gasName}",
                    1,
                    line = if (printBean.typeStatus == 0 && printBean.titleStatus == 0) 2 else 1
                )
            }
            if (printBean.titleStatus == 1) {
                setting(
                    printBean.titleContent,
                    alignment = 1,
                    line = if (printBean.typeStatus == 0) 2 else 1
                )
            }

            if (printBean.typeStatus == 1) {
                setting("充值小票", alignment = 1, line = 2)
            }

            if (printBean.orderNoStatus == 1) {
                setting("订单编号: ${paySuccessBean?.orderNo}")
            }
            if (printBean.operationStatus == 1) {
                setting("交易时间: ${paySuccessBean?.rechargeDate}")
            }
            setting("----------------------------------")

            setting("操作员：${paySuccessBean?.gasMan}")
            setting(
                "类型：${
                    when (paySuccessBean?.cardType) {
                        0 -> "汽油卡"
                        1 -> "柴油卡"
                        2 -> "通用钱包"
                        else -> "天然气"
                    }
                }"
            )
            setting("----------------------------------")

            setting("应收金额：${paySuccessBean?.receivable}")
            setting("赠送金额：${paySuccessBean?.give}")

            setting("----------------------------------")

            setting("实收金额：${paySuccessBean?.money}", size = 30f)

            setting("支付方式：扫码支付")
            setting("赠送积分：${paySuccessBean?.giveIntegral}")

            setting("----------------------------------")


            if (printBean.memberNameStatus == 1) {
                setting("会 员 名：${memberManageBean?.nickName}")
            }
            if (printBean.phoneStatus == 1) {
                setting("会员卡号：${memberManageBean?.phone}")
            }
            if (printBean.memberTypeStatus == 1) {
                setting(
                    "会员级别：${
                        when (memberManageBean?.memberType) {
                            1 -> "白银"
                            2 -> "黄金"
                            3 -> "白金"
                            4 -> "至尊"
                            else -> "神秘会员"
                        }
                    }"
                )
            }
            if (printBean.balanceStatus == 1) {
                if (paySuccessBean?.payModel != "扫码支付") {
                    setting("账户余额：${paySuccessBean?.balance}")
                }
            }
            if (printBean.integralStatus == 1) {
                setting("账户积分：0")
            }
            setting("----------------------------------")

            if (printBean.addressStatus == 1) {
                setting(
                    "地址：${CommonConstant.getUserInfo()?.address}",
                    line = if (printBean.contactStatus == 0) 2 else 1
                )
            }
            if (printBean.contactStatus == 1) {
                setting(
                    "电话：${CommonConstant.getUserInfo()?.gasPhone}",
                    line = if (printBean.footnoteStatus == 0) 3 else 2
                )
            }
            if (printBean.footnoteStatus == 1) {
                setting(printBean.footnote, alignment = 1, line = 4)
            }
        }
    }


    //微信收银打印小票
    fun sendWXCashierRawData(
        paySuccessBean: PaySuccessBean?,
        printBean: PrintBean? = CommonConstant.getPrintBean()
    ) {
        if (printBean == null) {
            "请重新获取打印设置信息".runOnUiThread()
            return
        }
        sunmiPrinterService!!.apply {
            if (printBean.shopStatus == 1) {
                setting(
                    "${CommonConstant.getUserInfo()?.gasName}",
                    1,
                    line = if (printBean.typeStatus == 0 && printBean.titleStatus == 0) 2 else 1
                )
            }
            if (printBean.titleStatus == 1) {
                setting(
                    printBean.titleContent,
                    alignment = 1,
                    line = if (printBean.typeStatus == 0) 2 else 1
                )
            }

            if (printBean.typeStatus == 1) {
                setting("加油小票", alignment = 1, line = 2)
            }

            if (printBean.orderNoStatus == 1) {
                setting("订单编号: ${paySuccessBean?.orderNo}")
            }
            if (printBean.operationStatus == 1) {
                setting("交易时间: ${paySuccessBean?.payDate}")
            }
            setting("----------------------------------")

            setting("操作员：${paySuccessBean?.gasMan}")
            if (paySuccessBean?.gunNumber != null) {
                setting("枪号：${paySuccessBean.gunNumber} 号枪")
            }
            setting(
                "型号：${paySuccessBean?.model}${
                    when (paySuccessBean?.typeId) {
                        1 -> "汽油"
                        2 -> "柴油"
                        else -> "天然气"
                    }
                }"
            )
            setting("单价：${paySuccessBean?.oilPrice}")
            setting("数量：${paySuccessBean?.oilsRise}L")
            setting("应收金额：${paySuccessBean?.totalPrice}")
            setting("优惠金额：${paySuccessBean?.discount}")

            setting("----------------------------------")


            setting("实收金额：${paySuccessBean?.actual}", size = 30f)
            setting("支付方式：${paySuccessBean?.payModel}")
//            setting("加油赠送： +0.00")
            setting("赠送积分： 0")

            setting("----------------------------------")

            if (printBean.memberNameStatus == 1) {
                setting("会 员 名：${paySuccessBean?.memberName}")
            }
            if (printBean.phoneStatus == 1) {
                setting("会员卡号：${paySuccessBean?.memberPhone}")
            }
            if (printBean.memberTypeStatus == 1) {
                setting(
                    "会员级别：${
                        when (paySuccessBean?.memberType) {
                            1 -> "白银"
                            2 -> "黄金"
                            3 -> "白金"
                            4 -> "至尊"
                            else -> "神秘会员"
                        }
                    }"
                )
            }
            if (printBean.balanceStatus == 1) {
                if (paySuccessBean?.payModel != "扫码支付") {
                    setting("账户余额：${paySuccessBean?.balance}")
                }
            }
            if (printBean.integralStatus == 1) {
                setting("账户积分：${paySuccessBean?.integral}")
            }
            setting("----------------------------------")


            if (printBean.addressStatus == 1) {
                setting(
                    "地址：${CommonConstant.getUserInfo()?.address}",
                    line = if (printBean.contactStatus == 0) 2 else 1
                )
            }
            if (printBean.contactStatus == 1) {
                setting(
                    "电话：${CommonConstant.getUserInfo()?.gasPhone}",
                    line = if (printBean.footnoteStatus == 0) 3 else 2
                )
            }

            if (printBean.footnoteStatus == 1) {
                setting(printBean.footnote, alignment = 1, line = 4)
            }
        }
    }


    //交班小票
    fun sendShiftHandoverRawData(handoverBean: HandoverBean?) {
        sunmiPrinterService?.apply {
            setting("${CommonConstant.getUserInfo()?.gasName}", 1, 30f)
            setting("操作员：${CommonConstant.getUserInfo()?.name}", alignment = 1)
            setting(
                "${CommonConstant.getUserInfo()?.startTime?.toYMDHMSDate()} 至 ${toCurrentYMDHMSDate()}",
                alignment = 1,
                size = 16f,
                line = 2
            )

            setting("销售情况", size = 30f)

            printColumn(arrayOf("支付方式", "销售", "退款"))
            printColumn(
                arrayOf(
                    "加 油 卡",
                    "￥${handoverBean?.refuelingCard}",
                    "￥${handoverBean?.refuelingCardRefund}"
                )
            )

            printColumn(
                arrayOf(
                    "通用钱包",
                    "￥${handoverBean?.wallet}",
                    "￥${handoverBean?.walletRefund}"
                )
            )
            printColumn(
                arrayOf(
                    "扫码支付",
                    "￥${handoverBean?.scanningCode}",
                    "￥${handoverBean?.scanningCodeRefund}"
                )
            )
            printColumn(
                arrayOf(
                    "应收汇总",
                    "￥${handoverBean?.should}",
                    "￥${handoverBean?.shouldRefund}"
                )
            )
            printColumn(
                arrayOf(
                    "实收汇总",
                    "￥${handoverBean?.actual}",
                    "￥${handoverBean?.actualRefund}"
                )
            )
            setting(
                "合计：￥${handoverBean?.totalSales}笔  共：￥ ${handoverBean?.totalPaidIn}",
                size = 20f,
                alignment = 2,
                line = 2
            )

            setting("充值情况", size = 30f)
            printColumn(arrayOf("支付方式", "充值金额", "赠送金额"))
            printColumn(
                arrayOf(
                    "加油卡",
                    "￥${handoverBean?.gasolineCard}",
                    "￥${handoverBean?.gasolineCardGive}"
                )
            )
            printColumn(
                arrayOf(
                    "柴油卡",
                    "￥${handoverBean?.dieselOilCard}",
                    "￥${handoverBean?.dieselOilCardGive}"
                )
            )
            printColumn(
                arrayOf(
                    "通用钱包",
                    "￥${handoverBean?.recharge}",
                    "￥${handoverBean?.rechargeGive}"
                )
            )
            printColumn(
                arrayOf(
                    "天然气卡",
                    "￥${handoverBean?.naturalGasCard}",
                    "￥${handoverBean?.naturalGasCardGive}"
                )
            )
            setting(
                "合计：￥${handoverBean?.totalRechargeAmount} 共：￥${handoverBean?.totalGifts}",
                2,
                line = 2
            )

            setting("会员统计", size = 30f)
            printColumn(
                arrayOf("新增会员", "${handoverBean?.newMembers}人"),
                intArrayOf(1, 1),
                intArrayOf(0, 2),
            )
            lineWrap(1, null)
            setting("优惠统计", size = 30f)
            printColumn(
                arrayOf("满减优惠金额", "￥${handoverBean?.fullDiscount}"),
                intArrayOf(1, 1),
                intArrayOf(0, 2),
            )
            printColumn(
                arrayOf("代金券优惠金额", "￥${handoverBean?.cashCoupon}"),
                intArrayOf(1, 1),
                intArrayOf(0, 2),
            )
            printColumn(
                arrayOf("油品优惠金额", "￥${handoverBean?.oils}"),
                intArrayOf(1, 1),
                intArrayOf(0, 2),
            )
            printColumn(
                arrayOf("积分抵现金额", "￥${handoverBean?.discount}"),
                intArrayOf(1, 1),
                intArrayOf(0, 2),
            )
            printColumn(
                arrayOf("优惠总金额", "￥${handoverBean?.totalAmount}"),
                intArrayOf(1, 1),
                intArrayOf(0, 2),
            )
            lineWrap(1, null)
            if (handoverBean?.oleicList?.isNotEmpty() == true) {
                setting("油优惠统计", size = 30f)
                handoverBean.oleicList.forEach {
                    printColumn(
                        arrayOf("${it.model}#优惠金额 ", "￥${it.price}"),
                        intArrayOf(1, 1),
                        intArrayOf(0, 2),
                    )
                }
            }
            lineWrap(3, null)
        }
    }
}


private fun SunmiPrinterService.setting(
    text: String?, alignment: Int = 0, size: Float = 22f, line: Int = 1
) {
    sendRAWData(byteArrayOf(0x1B, 0x45, 0x01), null) //字体加粗
//        sendRAWData(byteArrayOf(0x1B, 0x45, 0x0), null) //字体变细
    setAlignment(alignment, null)
    setFontSize(size, null)
    printText(text, null)
    lineWrap(line, null)
}


private fun SunmiPrinterService.printColumn(
    colsTextArr: Array<String>,
    colsWidthArr: IntArray = intArrayOf(1, 1, 1),
    colsAlign: IntArray = intArrayOf(0, 0, 0)
) {
    sendRAWData(byteArrayOf(0x1B, 0x45, 0x01), null) //字体加粗
    setFontSize(22f, null)
    printColumnsString(colsTextArr, colsWidthArr, colsAlign, null)
}

