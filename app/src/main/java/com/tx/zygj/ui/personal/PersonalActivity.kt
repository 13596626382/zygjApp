package com.tx.zygj.ui.personal

import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.bean.UserInfoBean
import com.llx.common.util.addAfterTextChanged
import com.llx.common.util.onBack
import com.llx.common.util.setOnSingleClickListener
import com.llx.common.util.toast
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityPersonalBinding

/**
 * 个人信息
 */
class PersonalActivity : BaseActivity<ActivityPersonalBinding>(R.layout.activity_personal) {
    private val model: PersonalViewModel by viewModels()
    private var userInfoBean: UserInfoBean? = null
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.changeCard.setOnClickListener {
            toast("功能开发中")
            return@setOnClickListener
//            startActivity<ConfirmCardActivity>()
        }
        userInfoBean = CommonConstant.getUserInfo()
        binding.userInfoBean = userInfoBean
        binding.userName.addAfterTextChanged {
            userInfoBean?.userName = this
        }
        binding.name.addAfterTextChanged {
            userInfoBean?.name = this
        }
        binding.phone.addAfterTextChanged {
            userInfoBean?.phone = this
        }

        binding.workNumber.addAfterTextChanged {
            userInfoBean?.workNumber = this
        }
        binding.confirm.setOnSingleClickListener {
            if (userInfoBean?.userName == "") {
                toast("用户不能为空")
                return@setOnSingleClickListener
            }
            if (userInfoBean?.name == "") {
                toast("操作员名不能为空")
                return@setOnSingleClickListener
            }
            if (userInfoBean?.phone == "") {
                toast("手机号不能为空")
                return@setOnSingleClickListener
            }
            if (userInfoBean?.phone?.length != 11) {
                toast("手机号格式不正确")
                return@setOnSingleClickListener
            }
            if (userInfoBean?.workNumber == "") {
                toast("工号不能为空")
                return@setOnSingleClickListener
            }
            model.updatePersonal(userInfoBean)
        }
        model.requestResult.observe(this) {
            onBack()
        }
    }
}