package com.tx.zygj.ui.login

import androidx.activity.viewModels
import com.llx.common.base.BaseActivity
import com.llx.common.util.finishAllActivity
import com.llx.common.util.showLoadingDialog
import com.llx.common.util.startActivity
import com.lxj.xpopup.impl.LoadingPopupView
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityLoginBinding
import com.tx.zygj.ui.main.MainActivity

//登录
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val model: LoginViewModel by viewModels()
    private lateinit var loadDialog: LoadingPopupView
    override fun initData() {
        loadDialog = showLoadingDialog("登录中")
        binding.login.setOnClickListener {
            val userName = binding.userName.text.toString().trim()
            val passWord = binding.passWord.text.toString().trim()
            if (userName == "") {
                binding.userName.error = "手机号不能为空"
                return@setOnClickListener
            }
            if (userName.length != 11) {
                binding.userName.error = "手机号格式不正确"
                return@setOnClickListener
            }
            if (passWord == "") {
                binding.passWord.error = "密码不能为空"
                return@setOnClickListener
            }
            if (passWord.length != 6) {
                binding.passWord.error = "密码长度不匹配,应为6位"
                return@setOnClickListener
            }
            loadDialog.show()
            model.login(userName, passWord)
        }

        model.login.observe(this) {
            loadDialog.dismiss()
            if (it) {
                startActivity<MainActivity>()
                finishAllActivity()
            }
        }

    }

    override fun getBackEnabled() = true

    override fun onBack() {
        finishAllActivity()
    }
}