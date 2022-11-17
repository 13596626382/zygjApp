package com.tx.zygj.ui.change

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.onBackActivity
import com.llx.common.util.textString
import com.llx.common.util.toast
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityChangePassBinding

/**
 * 修改密码
 */
class ChangePassActivity : BaseActivity<ActivityChangePassBinding>(R.layout.activity_change_pass) {
    private val model: ChangePassViewModel by viewModels()
    private var phone: String? = ""
    override fun initData() {
        binding.titleBar.setOnBack(this)
        phone = CommonConstant.getUserInfo()?.phone
        binding.phone.text = phone?.replace(phone?.subSequence(3, 7).toString(), "****")

        binding.changePass.setOnClickListener {
            val passWord = binding.password.textString()
            val newPassWord = binding.newPassword.textString()
            val confirmPassword = binding.confirmPassword.textString()
            if (passWord == "") {
                toast("请输入原密码")
                return@setOnClickListener
            }
            if (newPassWord == "") {
                toast("请新密码")
                return@setOnClickListener
            }
            if (newPassWord.length != 6) {
                toast("密码长度不匹配,应为6位")
                return@setOnClickListener
            }
            if (passWord == newPassWord) {
                toast("新旧密码相同")
                return@setOnClickListener
            }
            if (newPassWord != confirmPassword) {
                toast("密码两次输入不一致")
                return@setOnClickListener
            }
            model.changePass(phone, passWord, newPassWord, confirmPassword)

            model.requestResult.observe(this) {
                onBackActivity()
            }
        }
    }
}