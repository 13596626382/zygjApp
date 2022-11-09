package com.tx.zygj.ui.integral

import com.llx.common.base.BaseActivity
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityManualIntegralBinding

/**
 * 手动积分
 */
class ManualIntegralActivity : BaseActivity<ActivityManualIntegralBinding>(R.layout.activity_manual_integral) {
    override fun initData() {
        binding.titleBar.setOnBack(this)
    }
}