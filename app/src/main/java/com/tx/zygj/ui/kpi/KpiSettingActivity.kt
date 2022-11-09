package com.tx.zygj.ui.kpi

import com.llx.common.base.BaseActivity
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityKpiSettingBinding

/**
 * kpi设置
 */
class KpiSettingActivity : BaseActivity<ActivityKpiSettingBinding>(R.layout.activity_kpi_setting) {
    override fun initData() {
        binding.titleBar.setOnBack(this)
    }
}