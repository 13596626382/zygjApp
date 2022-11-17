package com.tx.zygj.ui.writeoff.record

import com.llx.common.base.BaseActivity
import com.llx.common.util.bind
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivityWriteOffRecordBinding

/**
 * 核销记录
 */
class WriteOffRecordActivity :
    BaseActivity<ActivityWriteOffRecordBinding>(R.layout.activity_write_off_record) {
    override fun initData() {
        binding.titleBar.setOnBack(this)
        binding.recyclerView.bind(WriteOffRecordAdapter()) {
            setList(listOf("", "", ""))
        }
    }
}