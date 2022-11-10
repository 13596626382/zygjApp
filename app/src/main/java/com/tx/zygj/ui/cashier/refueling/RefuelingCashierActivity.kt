package com.tx.zygj.ui.cashier.refueling

import android.content.Intent
import android.graphics.Paint
import android.view.View
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.bean.OilBean
import com.tx.zygj.bean.OilGunBean
import com.tx.zygj.bean.OilerBean
import com.tx.zygj.databinding.ActivityRefuerlingCashierBinding
import com.tx.zygj.dialog.oiler.OilerDialogFragment
import com.tx.zygj.ui.order.collection.CollectionOrderActivity
import com.tx.zygj.ui.search.SearchActivity

/**
 * 加油收银
 */
class RefuelingCashierActivity :
    BaseActivity<ActivityRefuerlingCashierBinding>(R.layout.activity_refuerling_cashier) {
    private val model: RefuelingCashierViewModel by viewModels()

    private val mTypeAdapter by lazy { OilTypeAdapter() } //油类型
    private val mModelAdapter by lazy { OilModelAdapter() } //油型号
    private val mGunAdapter by lazy { OilGunAdapter() } //油枪
    private lateinit var mDialogFragment: OilerDialogFragment //加油员

    private var oilListBean: ArrayList<OilBean>? = null //油品
    private var oilerListBean: ArrayList<OilerBean>? = null //加油员
    private var memberManageBean: MemberManageBean? = null //会员
    private var oilModelBean: OilBean.OilModelBean? = null //油类型
    private var oilGunBean: OilGunBean? = null //油枪
    private var oilerBean: OilerBean? = null //加油员

    private var keyboardNumber = "" //键盘输入的内容
    private var isOilGun = false //是否有油枪
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.search.setOnSingleClickListener {
            startActivityForResult(
                Intent(mContext, SearchActivity::class.java).putExtra(
                    CommonConstant.SEARCH_TYPE, true
                ), CommonConstant.REQUEST_CODE
            )
        }
        binding.mantle.setOnSingleClickListener {
            startActivityForResult(
                Intent(mContext, SearchActivity::class.java).putExtra(
                    CommonConstant.SEARCH_TYPE, true
                ), CommonConstant.REQUEST_CODE
            )
        }
        binding.settle.setOnSingleClickListener {
            gtToPay()
        }
        //加油金额
        binding.editOilsPrice.setOnSingleClickListener {
//            if (memberManageBean == null) {
//                toast("请选择会员")
//                binding.mantle.startAnimation(animation)
//                it.post {
//                    binding.scrollView.fullScroll(View.FOCUS_UP)
//                }
//                return@setOnSingleClickListener
//            }
            if (oilModelBean == null) {
                toast("请选择油类型")
                return@setOnSingleClickListener
            }
            if (isOilGun && oilGunBean == null) {
                toast("请选择油枪号")
                return@setOnSingleClickListener
            }

            binding.keyboard.visibility = View.VISIBLE
            it.post {
                binding.scrollView.fullScroll(View.FOCUS_DOWN) //滚动到底部
            }
        }
        //选择加油员
        binding.oilerLayout.setOnSingleClickListener {
            mDialogFragment = OilerDialogFragment()
            mDialogFragment.withArguments("oilerListBean" to oilerListBean)
            mDialogFragment.onClickListener = {
                oilerBean = it
                binding.oilerName.text = it.name
            }
            mDialogFragment.show(supportFragmentManager, CommonConstant.DIALOG_FRAGMENT)
        }
        //油品
        binding.oilTypeRecyclerView.bindHorizontal(mTypeAdapter) {
            setOnItemClickListener { _, _, position ->
                if (data[position].isCheck) {
                    return@setOnItemClickListener
                }
                data.forEach {
                    it.isCheck = false
                }
                data[position].isCheck = true
                notifyDataSetChanged()
                mModelAdapter.setList(data[position].oil)
                oilModelBean = null
                oilGunBean = null
                binding.oilGunLayout.visibility = View.GONE
                mGunAdapter.setList(null)
            }
        }
        //油型号
        binding.oilModelRecyclerView.bindGrid(mModelAdapter, 3) {
            setOnItemClickListener { _, _, position ->
//                if (memberManageBean == null) {
//                    toast("请选择会员")
//                    binding.mantle.startAnimation(animation)
//                    return@setOnItemClickListener
//                }
                if (data[position].isCheck) {
                    return@setOnItemClickListener
                }

                //所有油类型设置成未选中状态
                mTypeAdapter.data.forEach {
                    it.oil.forEach { it1 ->
                        it1.isCheck = false
                    }
                }
                //当前选中的油类型设置选中状态
                data[position].isCheck = true
                notifyDataSetChanged()

                oilModelBean = data[position]
//                checkMember()
                checkOilsType()
                //已输入加油金额挂更改油类型也要重新计算
                if (binding.editOilsPrice.textString() != "") {
                    calculation()
                }
                oilGunBean = null
                model.getOilGun(data[position].id)
            }
        }
        binding.oilGunRecyclerView.bindGrid(mGunAdapter, 4) {
            setOnItemClickListener { _, _, position ->
                if (data[position].isCheck) {
                    return@setOnItemClickListener
                }
                data.forEach {
                    it.isCheck = false
                }
                data[position].isCheck = true
                oilGunBean = data[position]
                notifyDataSetChanged()
            }
        }
        binding.integral.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG //下划线
        //从会员信息列表跳转吧
        memberManageBean = intentParcelableExtras(CommonConstant.MEMBER_BEAN)
        if (memberManageBean != null) {
            binding.memberBean = memberManageBean
            binding.member.visibility = View.VISIBLE
            binding.search.visibility = View.GONE
            binding.mantle.visibility = View.GONE
        }

        keyboardView()
//        checkMember()
        checkOilsType()
        model.apply {
            getOleice()
            getOiler(CommonConstant.getUserInfo()?.gasId)
            oilBean.observe(this@RefuelingCashierActivity) {
                oilListBean = it
                mTypeAdapter.setList(it)
                mModelAdapter.setList(it[0].oil)
            }
            oilGunBean.observe(this@RefuelingCashierActivity) {
                if (it.isNotEmpty()) {
                    mGunAdapter.setList(it)
                    binding.oilGunLayout.visibility = View.VISIBLE
                    isOilGun = true
                } else {
                    binding.oilGunLayout.visibility = View.GONE
                    isOilGun = false
                }
            }
            oilerBean.observe(this@RefuelingCashierActivity) {
                oilerListBean = it
            }
        }
    }

    //计算加油数量
    private fun calculation() {
        var value = binding.editOilsPrice.textString()
        if (value == "") {
            value = "0"
        }
        binding.oilsRise.text =
            stringBuild("约", value.division(oilModelBean?.price!!), "L")
    }
//
//    //检查是否选择会员
//    private fun checkMember() {
//        if (memberManageBean == null) {
//            binding.editOilsPrice.hint = "请选择会员"
//        } else {
//            checkOilsType()
//        }
//    }

    //检查是否选择油类型
    private fun checkOilsType() {
        if (oilModelBean == null) {
            binding.editOilsPrice.hint = "请选择油号"
        } else {
            binding.oilsPrice.text = stringBuild("挂牌价：", oilModelBean?.price, " 元/L")
            binding.editOilsPrice.hint = "输入加油金额"
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
            binding.numberSpot,
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
                binding.editOilsPrice.text = keyboardNumber

                if (number.isNotEmpty() && !number.endsWith(".")) {
                    calculation()
                }
                binding.price.text =
                    if (number.isEmpty()) "应收款：￥0" else stringBuild("应收款：￥", keyboardNumber)
            }
        }
        binding.delete.setOnClickListener {
            keyboardNumber = if (keyboardNumber.isEmpty()) "" else keyboardNumber.substring(
                0,
                keyboardNumber.length - 1
            )
            binding.price.text =
                if (keyboardNumber.isEmpty()) "应收款：￥0" else stringBuild("应收款：￥", keyboardNumber)
            binding.editOilsPrice.text = keyboardNumber.ifEmpty { "" }
            if (!keyboardNumber.endsWith(".")) {
                calculation()
            }
        }
    }


    private fun gtToPay() {
//        if (memberManageBean == null) {
//            toast("请选择会员")
//            return
//        }
        if (oilModelBean == null) {
            toast("请选择油类型")
            return
        }
        if (isOilGun && oilGunBean == null) {
            toast("请选择油枪号")
            return
        }
        if (oilerBean == null) {
            toast("请选择加油员")
            return
        }
        if (binding.editOilsPrice.textString() == "" ||
            binding.editOilsPrice.textString().toDouble() <= 0.00
        ) {
            toast("请输入正确加油金额")
            return
        }

        startActivity<CollectionOrderActivity>(
            CommonConstant.MEMBER_BEAN to memberManageBean,
            "price" to binding.editOilsPrice.textString(),
            "oilsRise" to binding.oilsRise.textString(),
            "oilModelBean" to oilModelBean,
            "oilGunBean" to oilGunBean,
            "oilerBean" to oilerBean,
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == CommonConstant.REQUEST_CODE) {
            memberManageBean = data?.intentParcelableExtras(CommonConstant.MEMBER_BEAN)
            binding.member.visibility = View.VISIBLE
            binding.mantle.visibility = View.GONE
            binding.memberBean = memberManageBean
//            checkMember()
        }
    }

    override fun update() {
        //如果支付成功，清除一下相关数据
        if (CommonConstant.isPaySuccess) {
            oilerBean = null
            //如果从会员信息跳转支付成功不隐藏
            if (intentParcelableExtras<MemberManageBean>(CommonConstant.MEMBER_BEAN) == null) {
                memberManageBean = null
                binding.member.visibility = View.GONE
            }
            binding.keyboard.visibility = View.GONE
            binding.editOilsPrice.text = ""
            binding.price.text = "应收款：￥0"
            binding.oilsRise.text = "约0 L"
            binding.oilerName.text = ""
            keyboardNumber = ""
            CommonConstant.isPaySuccess = false
        }
    }
}