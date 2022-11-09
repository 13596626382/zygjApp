package com.tx.zygj.ui.writeoff

import com.llx.common.base.BaseActivity
import com.llx.common.util.startActivity
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityWriteOffBinding
import com.tx.zygj.ui.writeoff.record.WriteOffRecordActivity

/**
 * 卡券核销
 */
class WriteOffActivity : BaseActivity<ActivityWriteOffBinding>(R.layout.activity_write_off) {
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.record.setOnClickListener {
            startActivity<WriteOffRecordActivity>()
        }
    }
}