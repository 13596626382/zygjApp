package com.tx.zygj.ui.member.level

import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityMemberLevelBinding
import com.tx.zygj.dialog.level.MemberLevelDialogFragment

/**
 * 会员等级
 */
class MemberLevelActivity :
    BaseActivity<ActivityMemberLevelBinding>(R.layout.activity_member_level) {

    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.levelSupreme.setOnClickListener {
            val fragment = MemberLevelDialogFragment()
            fragment.show(supportFragmentManager, CommonConstant.DIALOG_FRAGMENT)
        }
    }
}