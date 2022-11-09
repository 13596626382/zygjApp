package com.llx.common.bean

import java.util.*

data class UserInfoBean (
    val id: Int,
    var userName: String, //用户名
    var name: String, //姓名
    var phone: String, //手机号
    var gasName: String, //油站名
    var gasId: Int, //站点id
    var address: String, //油站地址
    var gasPhone: String, //油站手机号
    var workNumber: String, //工号
    var avatarUrl: String? = null, //头像
    val token: String,
    val startTime: Date //登录时间
)