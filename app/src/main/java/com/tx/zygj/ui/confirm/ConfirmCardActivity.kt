package com.tx.zygj.ui.confirm

import com.llx.common.base.BaseActivity
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityConfirmCardBinding

/**
 * 确认换卡
 */
class ConfirmCardActivity : BaseActivity<ActivityConfirmCardBinding>(R.layout.activity_confirm_card) {
    override fun initData() {
        binding.titleBar.setOnBack(this)
    }
}