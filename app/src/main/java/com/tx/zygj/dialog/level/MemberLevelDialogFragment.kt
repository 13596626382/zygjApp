package com.tx.zygj.dialog.level

import android.view.Gravity
import com.llx.common.base.BaseDialogFragment
import com.llx.common.util.dp
import com.tx.zygj.R
import com.tx.zygj.databinding.DialogMemberLevelBinding

/**
 * 会员等级
 */
class MemberLevelDialogFragment :
    BaseDialogFragment<DialogMemberLevelBinding>(R.layout.dialog_member_level) {
    override fun init() {
    }

    override fun getDialogGravity() = Gravity.CENTER

    override fun getDialogWidth() = 293.dp.toInt()

    override fun getDialogHeight() = 220.dp.toInt()
}