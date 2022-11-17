package com.tx.zygj.ui.wallet

import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.bean.OilerBean
import com.tx.zygj.bean.WalletRechargeMoneyBean
import com.tx.zygj.bean.WalletRechargeTypeBean
import com.tx.zygj.databinding.ActivityWalletRechargeBinding
import com.tx.zygj.databinding.ViewEdittextBinding
import com.tx.zygj.dialog.oiler.OilerDialogFragment
import com.tx.zygj.ui.order.wallet.WalletOrderActivity
import com.tx.zygj.ui.search.SearchActivity

/**
 * 钱包充值
 */
class WalletRechargeActivity :
    BaseActivity<ActivityWalletRechargeBinding>(R.layout.activity_wallet_recharge) {
    private val model: WalletRechargeViewModel by viewModels()
    private val mTypeAdapter by lazy { WalletRechargeTypeAdapter() }
    private val mMoneyAdapter by lazy { WalletRechargeMoneyAdapter() }
    private lateinit var mDialogFragment: OilerDialogFragment

    private var memberManageBean: MemberManageBean? = null //会员
    private var oilerListBean: ArrayList<OilerBean>? = null //加油员
    private var oilerBean: OilerBean? = null //加油员

    private lateinit var footView: ViewEdittextBinding
    private lateinit var animation: Animation

    private var cardType = 0 //钱包类型
    private var money = 0.00 //充值金额
    private var keyboardNumber = ""

    override fun initData() {
        animation = AnimationUtils.loadAnimation(mContext, R.anim.shake)
        footView = DataBindingUtil.bind(layoutInflater.inflate(R.layout.view_edittext, null))!!

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
        binding.typeRecyclerView.bindHorizontal(mTypeAdapter) {
            setOnItemClickListener { _, _, position ->
                if (data[position].isCheck) return@setOnItemClickListener
                data.forEach {
                    it.isCheck = false
                }
                data[position].isCheck = true
                cardType = position
                notifyDataSetChanged()
            }
            setList(
                arrayListOf(
                    WalletRechargeTypeBean("汽油", true),
                    WalletRechargeTypeBean("柴油"),
                    WalletRechargeTypeBean("通用钱包"),
                    WalletRechargeTypeBean("天然气")
                )
            )
        }
        binding.moneyRecyclerView.bindGrid(mMoneyAdapter, 3) {
            setOnItemClickListener { _, _, position ->
                if (data[position].isCheck) return@setOnItemClickListener

                data.forEach {
                    it.isCheck = false
                }
                data[position].isCheck = true
                money = data[position].money.toDouble()
                footView.money.background = getCompatDrawable(R.drawable.fillet_707070_4)
                footView.money.setTextColor(getCompatColor(R.color.color_3D4255))
                binding.keyboard.visibility = View.GONE
                notifyDataSetChanged()
            }
            footerViewAsFlow = true
            setList(
                arrayListOf(
                    WalletRechargeMoneyBean("50", false),
                    WalletRechargeMoneyBean("100", false),
                    WalletRechargeMoneyBean("200", false),
                    WalletRechargeMoneyBean("500", false),
                    WalletRechargeMoneyBean("1000", false)
                )
            )
            addFooterView(footView.root)
        }
        binding.rechargeWallet.setOnSingleClickListener {
            goToPay()
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
        //选择金额
        footView.money.setOnSingleClickListener {
            if (binding.keyboard.visibility == View.VISIBLE) return@setOnSingleClickListener
            mMoneyAdapter.data.forEach { it1 ->
                it1.isCheck = false
            }
            mMoneyAdapter.notifyDataSetChanged()
            binding.keyboard.visibility = View.VISIBLE
            footView.money.background = getCompatDrawable(R.drawable.fillet_f38b26_5)
            footView.money.setTextColor(getCompatColor(R.color.color_F38B26))
            if (keyboardNumber != "" && !keyboardNumber.endsWith(".")) {
                money = keyboardNumber.toDouble()
            }
            it.post {
                binding.scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }

        memberManageBean = intentParcelableExtras(CommonConstant.MEMBER_BEAN)
        if (memberManageBean != null) {
            binding.search.visibility = View.GONE
            binding.mantle.visibility = View.GONE
            binding.member.visibility = View.VISIBLE
            binding.memberBean = memberManageBean
        }
        model.getOiler()
        model.oilerBean.observe(this) {
            oilerListBean = it
        }
        keyboardView()
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
            binding.numberSpot
        )
        numberArray.forEach {
            it.setOnClickListener { _ ->
                val number = it.text.toString()
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
                footView.money.text = keyboardNumber
                if (!keyboardNumber.endsWith(".")) {
                    money = keyboardNumber.toDouble()
                }
            }
        }
        binding.delete.setOnClickListener {
            keyboardNumber = if (keyboardNumber.isEmpty()) "" else keyboardNumber.substring(
                0,
                keyboardNumber.length - 1
            )
            money = keyboardNumber.ifEmpty { "0" }.toDouble()
            footView.money.text = keyboardNumber.ifEmpty { "" }

        }

    }

    private fun goToPay() {
        if (checkMember()) {
            toast("请选择会员")
            binding.mantle.startAnimation(animation)
            return
        }
        if (oilerBean == null) {
            toast("请选择加油员")
            return
        }
        if (money == 0.00) {
            toast("请选择或输入充值金额")
            return
        }
        binding.keyboard.visibility = View.GONE
        startActivity<WalletOrderActivity>(
            CommonConstant.MEMBER_BEAN to memberManageBean,
            "price" to money.toString(),
            "cardType" to cardType,
            "oilerBean" to oilerBean,
        )
    }

    private fun checkMember() = memberManageBean == null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == CommonConstant.REQUEST_CODE) {
            memberManageBean = data?.intentParcelableExtras(CommonConstant.MEMBER_BEAN)
            binding.member.visibility = View.VISIBLE
            binding.mantle.visibility = View.GONE
            binding.memberBean = memberManageBean
        }
    }
}