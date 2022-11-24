package com.tx.zygj.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsBean(
    var id: Int? = null,
    var barCode: String? = null, //条码
    var goodsName: String? = null, //商品名称
    var goodsClassifyId: Int? = null,//商品分类ID
    var sellingPrice: Double? = null, //销售价格
    var marketPrice: Double? = null, //市场价格
    var costPrice: Double? = null, //成本价格
    var goodsUnit: String? = null, //商品单位
    var bonusPoints: Int? = null, //赠送积分
    var stock: Int? = null, //库存
    var publishOrNot: Int? = null,//是否发布
    var mallRelease: Int? = null, //商城发布
    var recommend: Int? = null,//是否推荐
    var hotOrNot: Int? = null,//是否热门
    var goodsPicture: String? = null, //商品图片
    var goodsDescribe: String? = null, //商品描述
    var gasId: Int? = null,//油站ID
    var goodsOrGift: Int,//商品或礼品
    var quantity: Int = 0,//购买数量
) : Parcelable