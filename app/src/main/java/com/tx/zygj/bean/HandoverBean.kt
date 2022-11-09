package com.tx.zygj.bean

data class HandoverBean(
    val refuelingCard: Double, //加油卡实收
    val wallet: Double, //通用钱包实收
    val scanningCode: Double, //扫码实收
    val refuelingCardRefund: Double, //加油卡退款
    val walletRefund: Double, //通用钱包退款
    val scanningCodeRefund: Double, //扫码退款
    val actual: Double, //实收汇总
    val actualRefund: Double, //实收退款
    val cashCoupon: Double, //代金券优惠金额
    val dieselOilCard: Double, //柴油卡充值金额
    val dieselOilCardGive: Double, //柴油卡赠送金额
    val discount: Double, //积分折现金额
    val fullDiscount: Double, //满减优惠
    val gasolineCard: Double, //汽油卡充值金额
    val gasolineCardGive: Double, //汽油卡赠送金额
    val integral: Double, //折现积分
    val naturalGasCard: Double, //天然气卡充值金额
    val naturalGasCardGive: Double, //天然气卡赠送金额
    val newMembers: Int, //新增会员
    val noZero: Double, //抹零金额
    val oils: Double, //油品优惠金额
    val recharge: Double, //通用钱包充值金额
    val rechargeGive: Double, //通用钱包赠送金额
    val should: Double, //应收汇总
    val shouldRefund: Double, //应该退款
    val total: Double, //共计
    val totalAmount: Double, //优惠总金额
    val totalGifts: Double, //赠送合计
    val totalPaidIn: Double, //实收合计
    val totalReceivables: Double, //应收
    val totalRecharge: Double, //合计
    val totalRechargeAmount: Double, //合计充值金额
    val oleicList: ArrayList<OilsBean>,
    val totalSales: Int //销售合计
) {
    data class OilsBean(
        val price: Double,
        val model: Int,
    )
}