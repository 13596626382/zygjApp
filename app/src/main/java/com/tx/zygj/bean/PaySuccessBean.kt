package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaySuccessBean(
    val orderNo: String? = null,
    val userId: String? = null,
    val model: String? = null,
    val typeId: Int? = null,
    val gasId: String? = null,
    val gasMan: String? = null,
    val totalPrice: String? = null,
    val payDate: String? = null,
    val discount: String? = null,
    val actual: String? = null,
    val gasName: String? = null,
    val balance: String? = null,
    val oilPrice: String? = null,
    val payModel: String? = null,
    val oilsRise: String? = null,

    val rechargeDate: String? = null, //钱包充值交易时间
    val cardType: Int = 0, //钱包充值类型
    val receivable: String? = null, //钱包充值应收金额
    val give: String? = null, //钱包充值赠送金额
    val giveIntegral: String? = null, //钱包充值赠送积分
    val money: String? = null, //钱包充值实收金额
    val gunNumber: String? = null, //油枪号
    val memberName: String? = null, //会员名称
    val memberPhone: String? = null, //会员手机号
    val memberType: Int = 0, //会员级别
    val integral: String? = null, //会员积分


) : Parcelable