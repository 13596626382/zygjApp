package com.tx.zygj.api

import com.llx.common.api.ApiBean
import com.llx.common.api.RefreshApiBean
import com.llx.common.bean.PrintBean
import com.llx.common.bean.UserInfoBean
import com.tx.zygj.bean.*
import okhttp3.RequestBody
import retrofit2.http.*

interface Api {

    /**
     * 登录
     * @param phone 手机号
     * @param passWord 密码
     */
    @FormUrlEncoded
    @POST("pos/user/login")
    suspend fun login(
        @Field("phone") phone: String,
        @Field("password") passWord: String
    ): ApiBean<UserInfoBean>

    /**
     * 获取今日业绩
     */
    @GET("pos/user/statistics")
    suspend fun getStatistics(): ApiBean<StatisticsBean>

    /**
     * 修改个人信息
     * @param id 用户id
     * @param userName 用户名
     * @param name 姓名
     * @param phone 手机号
     * @param workNumber 工号
     */
    @FormUrlEncoded
    @POST("pos/user/updatePosUser")
    suspend fun updatePersonal(
        @Field("id") id: Int?,
        @Field("userName") userName: String?,
        @Field("name") name: String?,
        @Field("phone") phone: String?,
        @Field("workNumber") workNumber: String?,
    ): ApiBean<String>

    /**
     * 修改密码
     * @param phone 手机号
     * @param passWord 原密码
     * @param newPassWord 新密码
     * @param confirmPassword 确认密码密码
     */
    @FormUrlEncoded
    @POST("pos/user/updatePassword")
    suspend fun changePass(
        @Field("phone") phone: String?,
        @Field("oldPassword") passWord: String,
        @Field("newPassword") newPassWord: String,
        @Field("newPassword2") confirmPassword: String,
    ): ApiBean<String>


    /**
     * 会员管理
     */
    @GET("pos/member/getMember")
    suspend fun getMember(): ApiBean<ArrayList<MemberManageBean>>

    /**
     * 获取会员信息
     * @param id 会员id
     */
    @FormUrlEncoded
    @POST("pos/member/toUpdateMember")
    suspend fun getMemBerMsg(@Field("id") id: Int?): ApiBean<MemberManageBean>


    /**
     * 扫码获取会员信息
     * @param id 会员id
     */
    @FormUrlEncoded
    @POST("pos/member/findByPhone")
    suspend fun getMemBerMsgPhone(@Field("id") id: Int?): ApiBean<MemberManageBean>

    /**
     * 查找会员
     * @param like 搜索条件 姓名 手机号 车牌号暂无
     */
    @FormUrlEncoded
    @POST("pos/member/likeMembers")
    suspend fun likeMembers(
        @Field("by") like: String,
        @Field("pageCurrent") page: Int
    ): RefreshApiBean<MemberManageBean>

    /**
     * 添加会员信息
     * @param nickName 用户昵称
     * @param phone 手机号
     * @param birthday 生日
     * @param memberType 会员类型
     * @param carNumber 车牌号
     * @param firm 所属单位
     * @param gender 性别
     * @param state 会员状态
     * @param password 消费密码
     * @param recommend 推荐人
     * @param applicant 办理人
     * @param place 办理站点
     */
    @FormUrlEncoded
    @POST("pos/member/addMember")
    suspend fun addMember(
        @Field("nickName") nickName: String?,
        @Field("phone") phone: String?,
        @Field("birthday") birthday: String?,
        @Field("memberType") memberType: Int?,
        @Field("carNumber") carNumber: String?,
        @Field("firm") firm: String?,
        @Field("gender") gender: String?,
        @Field("state") state: String?,
        @Field("password") password: String?,
        @Field("recommend") recommend: String?,
        @Field("applicant") applicant: String,
        @Field("place") place: String,
    ): ApiBean<String>


    /**
     * 修改会员信息
     * @param id 会员id
     * @param nickName 用户昵称
     * @param phone 手机号
     * @param birthday 生日
     * @param memberType 会员类型
     * @param carNumber 车牌号
     * @param firm 所属单位
     * @param gender 性别
     * @param state 会员状态
     * @param password 消费密码
     * @param recommend 推荐人
     * @param applicant 办理人
     * @param place 办理站点
     */
    @FormUrlEncoded
    @POST("pos/member/updateMember")
    suspend fun updateMember(
        @Field("id") id: Int?,
        @Field("nickName") nickName: String?,
        @Field("phone") phone: String?,
        @Field("birthday") birthday: String?,
        @Field("memberType") memberType: Int?,
        @Field("carNumber") carNumber: String?,
        @Field("firm") firm: String?,
        @Field("gender") gender: String?,
        @Field("state") state: String?,
        @Field("password") password: String?,
        @Field("recommend") recommend: String?,
        @Field("applicant") applicant: String,
        @Field("place") place: String,
    ): ApiBean<String>

    /**
     * 获取车牌
     * @param phone 会员手机号
     */
    @FormUrlEncoded
    @POST("pos/car/getCarNumber")
    suspend fun getCarNumber(@Field("phone") phone: String?): ApiBean<ArrayList<CarBrandBean>>

    /**
     * 添加车牌
     * @param carName 车辆名称
     * @param carNumber 车牌号
     * @param phone 手机号
     * @param state 默认
     */
    @FormUrlEncoded
    @POST("pos/car/addCarNumber")
    suspend fun addCarNumber(
        @Field("carName") carName: String?,
        @Field("carNumber") carNumber: String?,
        @Field("phone") phone: String?,
        @Field("state") state: Int?
    ): ApiBean<String>

    /**
     * 修改车牌信息
     * @param id 车牌id
     * @param carName 车辆名称
     * @param carNumber 车牌号
     * @param phone 手机号
     * @param state 默认
     */
    @FormUrlEncoded
    @POST("pos/car/updateCarNumber")
    suspend fun updateCarNumber(
        @Field("id") id: Int?,
        @Field("carName") carName: String?,
        @Field("carNumber") carNumber: String?,
        @Field("phone") phone: String?,
        @Field("state") state: Int?
    ): ApiBean<String>


    /**
     * 删除车牌信息
     * @param id 车牌id
     */
    @FormUrlEncoded
    @POST("pos/car/deleteCarNumber")
    suspend fun deleteCarNumber(@Field("id") id: Int?): ApiBean<String>


    /**
     * 获取消费记录
     * @param memberPhone 会员手机号
     * @param years 年月
     */
    @FormUrlEncoded
    @POST("pos/order/orderList")
    suspend fun getOrderList(
        @Field("memberPhone") memberPhone: String?,
        @Field("years") years: String?
    ): ApiBean<ArrayList<ConsumeBean>>

    /**
     * 删除消费记录
     * @param id 消费记录id
     */
    @FormUrlEncoded
    @POST("pos/order/deleteOrder")
    suspend fun deleteOrder(@Field("id") id: Int): ApiBean<String>


    /**
     * 获取充值记录
     * @param memberPhone 会员手机号
     */
    @FormUrlEncoded
    @POST("pos/findRecharges")
    suspend fun findRecharges(
        @Field("memberPhone") memberPhone: String?
    ): ApiBean<ArrayList<MemberRechargeRecordBean>>


    /**
     * 删除充值记录
     * @param id 充值记录id
     */
    @FormUrlEncoded
    @POST("pos/deleteRecharges")
    suspend fun deleteRecharges(@Field("id") id: Int): ApiBean<String>

    /**
     * 获取油品
     * @param id 油站id
     */
    @FormUrlEncoded
    @POST("pos/oleic/getOleice")
    suspend fun getOleice(@Field("gasId") id: Int?): ApiBean<ArrayList<OilBean>>

    /**
     * 获取油枪号
     * @param id 油类型id
     */
    @FormUrlEncoded
    @POST("pos/oleic/getOilGun")
    suspend fun getOilGun(@Field("id") id: Int?): ApiBean<ArrayList<OilGunBean>>

    /**
     * 获取加油员
     * @param gasId 油站id
     */
    @FormUrlEncoded
    @POST("pos/oleic/getGasMan")
    suspend fun getOiler(@Field("gasId") gasId: Int?): ApiBean<ArrayList<OilerBean>>

    /**
     * 设置油价
     */
    @POST("pos/oleic/updateOleic")
    suspend fun updateOleic(@Body oil: RequestBody): ApiBean<String>


    /**
     *  生成订单
     *  @param model 油型号
     *  @param typeId 油类型
     *  @param gasId 油站id
     *  @param gasMan 加油员
     *  @param Actual 实际支付金额
     *  @param totalPrice 应收金额
     *  @param memberPhone 会员手机号
     *  @param payModel 加油方式
     *  @param oilsRise 加油数量
     *  @param oilPrice 单价
     *  @param gunNumber 加油枪号
     */
    @FormUrlEncoded
    @POST("pos/order/create")
    suspend fun generateOrder(
        @Field("model") model: String? = "",
        @Field("typeId") typeId: Int? = 0,
        @Field("gasId") gasId: Int? = 0,
        @Field("gasMan") gasMan: String? = "",
        @Field("actual") Actual: Double? = 0.00,
        @Field("totalPrice") totalPrice: Double? = 0.00,
        @Field("memberPhone") memberPhone: String? = "",
        @Field("payModel") payModel: String? = "",
        @Field("oilsRise") oilsRise: Double? = 0.00,
        @Field("oilPrice") oilPrice: Double? = 0.00,
        @Field("gunNumber") gunNumber: Int? = 0,
    ): ApiBean<String>

    /**
     * 钱包充值生成订单
     * @param cardType 0 1 2 3 汽油卡 柴油卡 通用钱包 天然气
     * @param memberPhone 微信id
     * @param money 充值金额
     * @param gesId 油站id
     * @param gasMan 加油员
     * @param give 赠送金额
     * @param receivable 应收金额
     */
    @FormUrlEncoded
    @POST("pos/recharge")
    suspend fun findOrderNo(
        @Field("cardType") cardType: Int,
        @Field("memberPhone") memberPhone: String?,
        @Field("money") money: Double,
        @Field("gesId") gesId: Int?,
        @Field("gasMan") gasMan: String?,
        @Field("give") give: Double = 0.00,
        @Field("receivable") receivable: Double,
    ): ApiBean<String>

    /**
     * 快速收银生成订单
     * @param
     */
    @FormUrlEncoded
    @POST("pos/order/expressCashier")
    suspend fun getFastOrder(
        @Field("gasId") gasId: Int? = 0,
        @Field("actual") Actual: Double? = 0.00,
    ): ApiBean<String>

    /**
     * 加油卡支付
     * @param orderNo 订单号
     * @param memberPhone 会员手机号
     * @param actual 支付金额
     * @param typeId 加油卡类型
     */
    @FormUrlEncoded
    @POST("my/order/card")
    suspend fun oidCardPayment(
        @Field("OrderNo") orderNo: String,
        @Field("memberPhone") memberPhone: String?,
        @Field("actual") actual: Double,
        @Field("typeId") typeId: Int?
    ): ApiBean<PaySuccessBean>


    /**
     * 通用钱包支付
     * @param orderNo 订单号
     * @param memberPhone 会员手机号
     * @param actual 支付金额
     */
    @FormUrlEncoded
    @POST("my/order/cardPayment")
    suspend fun cardPayment(
        @Field("OrderNo") orderNo: String,
        @Field("memberPhone") memberPhone: String?,
        @Field("actual") actual: Double
    ): ApiBean<PaySuccessBean>


    /**
     * 扫码支付
     * @param authCode 微信付款码
     * @param tradeNo 订单号
     * @param totalFee 总金额
     * @param state 类型 0 消费 1 充值
     */
    @FormUrlEncoded
    @POST("pos/tradePay/pay")
    suspend fun pay(
        @Field("authCode") authCode: String,
        @Field("tradeNo") tradeNo: String,
        @Field("totalFee") totalFee: Double,
        @Field("state") state: Int = 0,
    ): ApiBean<PaySuccessBean>


    /**
     * 快速收银支付
     * @param authCode 微信付款码
     * @param tradeNo 订单号
     * @param totalFee 总金额
     * @param state 类型 0 消费 1 充值
     */
    @FormUrlEncoded
    @POST("pos/tradePay/pay")
    suspend fun fastPay(
        @Field("authCode") authCode: String,
        @Field("tradeNo") tradeNo: String,
        @Field("totalFee") totalFee: Double,
        @Field("state") state: Int = 2,
    ): ApiBean<PaySuccessBean>


    /**
     * 交班信息
     * @param phone 操作员电话号
     */
    @GET("pos/getExchange")
    suspend fun getExchange(@Query("phone") phone: String?): ApiBean<HandoverBean>


    /**
     * 消费记录详情
     * @param id 消费记录id
     */
    @FormUrlEncoded
    @POST("pos/order/getById")
    suspend fun getConsumeRecord(@Field("id") id: Int): ApiBean<PaySuccessBean>

    /**
     * 充值记录详情
     * @param id 充值记录id
     */
    @FormUrlEncoded
    @POST("pos/getById")
    suspend fun getRechargeRecord(@Field("id") id: Int): ApiBean<PaySuccessBean>

    /**
     * 对账单
     */
    @GET("pos/user/orderToday")
    suspend fun getOrderToday(): ApiBean<ArrayList<ReconciliationBean>>

    /**
     * 对账单搜索接口
     * @param by 搜索条件
     */
    @FormUrlEncoded
    @POST("pos/user/orderTodayLike")
    suspend fun getOrderTodayLike(@Field("by") by: String = ""): ApiBean<ArrayList<ReconciliationBean>>

    /**
     * 对账单详情
     * @param id 对账单id
     * @param typeId 类型
     */
    @FormUrlEncoded
    @POST("pos/user/getDetails")
    suspend fun getOrderTodayDetails(
        @Field("id") id: Int,
        @Field("typeId") typeId: String?
    ): ApiBean<PaySuccessBean>

    /**
     * 获取打印设置
     */
    @GET("admin/findTickets")
    suspend fun getPrintSettings(): ApiBean<PrintBean>

    /**
     * 修改打印设置
     */
    @FormUrlEncoded
    @POST("admin/updateTickets")
    suspend fun updatePrintSettings(
        @Field("id") id: Int?,
        @Field("addressStatus") addressStatus: Int?,
        @Field("balanceStatus") balanceStatus: Int?,
        @Field("carNumberStatus") carNumberStatus: Int?,
        @Field("contactStatus") contactStatus: Int?,
        @Field("footnoteStatus") footnoteStatus: Int?,
        @Field("footnote") footnote: String?,
        @Field("integralStatus") integralStatus: Int?,
        @Field("logo") logo: String?,
        @Field("logoStatus") logoStatus: Int?,
        @Field("memberNameStatus") memberNameStatus: Int?,
        @Field("memberTypeStatus") memberTypeStatus: Int?,
        @Field("operationStatus") operationStatus: Int?,
        @Field("operatorStatus") operatorStatus: Int?,
        @Field("orderNoStatus") orderNoStatus: Int?,
        @Field("phoneStatus") phoneStatus: Int?,
        @Field("remarksStatus") remarksStatus: Int?,
        @Field("shopStatus") shopStatus: Int?,
        @Field("titleContent") titleContent: String?,
        @Field("titleStatus") titleStatus: Int?,
        @Field("twoCode") twoCode: String?,
        @Field("twoCodeStatus") twoCodeStatus: Int?,
        @Field("typeStatus") typeStatus: Int?
    ): ApiBean<String>


    /**
     * 扫码获取会员信息
     * @param phone 扫码获取的手机号
     */
    @FormUrlEncoded
    @POST("pos/member/findByPhone")
    suspend fun findByPhone(@Field("phone") phone: String): ApiBean<MemberManageBean>

    /**
     * 交班查询密码
     * @param phone 登录账号
     * @param pw 输入的密码
     */
    @FormUrlEncoded
    @POST("pos/handover")
    suspend fun handover(
        @Field("phone") phone: String?,
        @Field("password") passWord: String
    ): ApiBean<MemberManageBean>
}