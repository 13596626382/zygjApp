package com.tx.zygj.ui.member.register

import android.view.View
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.bean.MemberManageBean
import com.tx.zygj.databinding.ActivityMemberRegisterBinding

/**
 * 会员登记/添加会员
 */
class MemberRegisterActivity :
    BaseActivity<ActivityMemberRegisterBinding>(R.layout.activity_member_register) {
    private val model: MemberRegisterViewModel by viewModels()
    private lateinit var memberManageBean: MemberManageBean
    private var title = ""
    override fun initData() {
        binding.titleBar.setOnBack(this)
        title = intentStringExtras(CommonConstant.INTENT_TITLE)!!
        binding.state.isChecked = true
        binding.titleBar.setTitle(title)
        val intent = intentParcelableExtras<MemberManageBean>(CommonConstant.MEMBER_BEAN)
        if (intent != null) {
            binding.memberBean = intent
            memberManageBean = intent
            binding.cardLayout.visibility = View.GONE
        } else {
            memberManageBean = MemberManageBean()
        }
        binding.name.addAfterTextChanged { memberManageBean.nickName = this }
        binding.phone.addAfterTextChanged { memberManageBean.phone = this }
        binding.carNumber.addAfterTextChanged { memberManageBean.carNumber = this }
        binding.firm.addAfterTextChanged { memberManageBean.firm = this }
        binding.passWord.addAfterTextChanged { memberManageBean.password = this }
        binding.recommend.addAfterTextChanged { memberManageBean.recommend = this }
        binding.layoutSex.showDialog(arrayListOf("男", "女"), this) { s, _ ->
            binding.sex.text = s
            memberManageBean.gender = s
        }
        binding.layoutBirthday.showDateDialog {
            binding.birthday.text = it.toYMDDateString()
            memberManageBean.birthday = it.toYMDDateString()
        }
        binding.layoutMemberLive.showDialog(
            arrayListOf("白银会员", "黄金会员", "白金会员", "至尊会员"),
            this
        ) { s, i ->
            binding.memberLive.text = s
            memberManageBean.memberType = i + 1
        }

        binding.save.setOnSingleClickListener {
            if (memberManageBean.nickName == "") {
                toast("请填写用户名")
                return@setOnSingleClickListener
            }
            if (memberManageBean.phone.isPhone()) {
                toast("用户手机号格式不正确")
                return@setOnSingleClickListener
            }
            if (memberManageBean.memberType == -1) {
                toast("请选择会员等级")
                return@setOnSingleClickListener
            }

            if (title == "修改信息") {
                model.updateMember(memberManageBean)
            } else {
                if (memberManageBean.carNumber == "") {
                    toast("请选择会员等级")
                    return@setOnSingleClickListener
                }
                model.addMember(memberManageBean)
            }
            memberManageBean.state = if (binding.state.isChecked) "1" else "0"

            model.requestResult.observe(this) {
                onBack()
            }
        }
    }

}