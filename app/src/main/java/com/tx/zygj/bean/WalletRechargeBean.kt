package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalletRechargeBean(
    val gasolineCard: ArrayList<RechargeActivityBean>, //汽油卡
    val dieselOilCard: ArrayList<RechargeActivityBean>, //柴油卡
    val currency: ArrayList<RechargeActivityBean>, //通用钱包
    val naturalGasCard: ArrayList<RechargeActivityBean>, //天然气卡
) : Parcelable {
    @Parcelize
    data class RechargeActivityBean(
        val id: Int,
        val activityName: String,
        val oilCardType: String,
        val startTime: String,
        val endTime: String,
        val rechargeAmount: Double,
        val give: Double,
        val giveIntegral: Int,
        val gasId: Int,
    ) : Parcelable
}