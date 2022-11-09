package com.llx.common

import com.llx.common.bean.PrintBean
import com.llx.common.bean.UserInfoBean

object CommonConstant {
    const val STRING_DIALOG_DATA = "string_dialog_data"
    const val STRING_DIALOG_FRAGMENT = "string_dialog_fragment"
    const val DIALOG_FRAGMENT = "dialog_fragment"
    const val INTENT_TITLE = "intent_title"
    const val SHOP_TYPE = "shop_type"
    const val SEARCH_TYPE = "is_black"
    const val SCAN_TYPE = "is_black"
    const val MEMBER_ID = "member_id"
    const val MEMBER_PHONE = "member_phone"
    const val MEMBER_BEAN = "member_bean"
    const val MEMBER_OPENID = "member_openid"
    const val CAR_BRAND_BEAN = "car_brand_bean"
    const val SCAN_CODE = "scan_code"
    const val FAST_CLICK_DELAY_TIME = 500 // 快速点击间隔
    const val REQUEST_CODE = 0X0001 // 快速点击间隔

    //是否支付成功
    var isPaySuccess = false

    private var userInfo: UserInfoBean? = null
    private var token: String? = ""
    private var printBean: PrintBean? = null
    private val orderList: ArrayList<String?> = arrayListOf()

    //设置用户信息
    fun setUserInfo(userInfoBean: UserInfoBean?) {
        this.userInfo = userInfoBean
        this.token = userInfoBean?.token
    }

    //获取登录信息
    fun getUserInfo() = userInfo

    //清空登录信息
    fun cleanUserInfo() {
        this.userInfo = null
        this.token = ""
    }
    //获取token
    fun getToken() = token
    //添加打印设置
    fun setPrintBean(printBean: PrintBean?) {
        this.printBean = printBean
    }
    //获取打印设置
    fun getPrintBean() = printBean
    //添加订单
    fun setOrderNo(order: String?) {
        orderList.add(order)
    }
    //检查是否打印过订单
    fun  isOrder(order: String?) = orderList.contains(order)
}