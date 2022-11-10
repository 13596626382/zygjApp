package com.tx.zygj.bean

data class WalletRechargeBean(
    val dieselOilCard: ArrayList<DieselOilCardBean>, //柴油卡
    val gasolineCard: ArrayList<GasolineCardBean>, //汽油卡
    val currency: ArrayList<CurrencyBean>, //通用钱包
    val naturalGasCard: ArrayList<NaturalGasCardBean>, //天然气卡
) {
    data class DieselOilCardBean(
        val id: Int,
        val activityName: String,
        val oilCardType: String,
        val startTime: String,
        val endTime: String,
        val rechargeAmount: Int,
        val give: Int,
        val giveIntegral: Int,
        val gasId: Int,
    )

    data class GasolineCardBean(
        val id: Int,
        val activityName: String,
        val oilCardType: String,
        val startTime: String,
        val endTime: String,
        val rechargeAmount: Int,
        val give: Int,
        val giveIntegral: Int,
        val gasId: Int,
    )

    data class CurrencyBean(
        val id: Int,
        val activityName: String,
        val oilCardType: String,
        val startTime: String,
        val endTime: String,
        val rechargeAmount: Int,
        val give: Int,
        val giveIntegral: Int,
        val gasId: Int,
    )

    data class NaturalGasCardBean(
        val id: Int,
        val activityName: String,
        val oilCardType: String,
        val startTime: String,
        val endTime: String,
        val rechargeAmount: Int,
        val give: Int,
        val giveIntegral: Int,
        val gasId: Int,
    )
}