package com.tx.zygj.dialog.oiler

import android.view.WindowManager
import com.llx.common.base.BaseDialogFragment
import com.llx.common.util.argumentsParcelableArrayList
import com.llx.common.util.bind
import com.llx.common.util.setOnSingleClickListener
import com.tx.zygj.R
import com.tx.zygj.bean.OilerBean
import com.tx.zygj.databinding.DialogOilerBinding

class OilerDialogFragment : BaseDialogFragment<DialogOilerBinding>(R.layout.dialog_oiler) {
    private val mAdapter by lazy { OilerDialogAdapter() }
    lateinit var onClickListener: (OilerBean) -> Unit
    override fun init() {
        binding.recyclerView.bind(mAdapter) {
            setOnItemClickListener { _, _, position ->
                onClickListener.invoke(mAdapter.data[position])
                dismiss()
            }
        }.setList(argumentsParcelableArrayList("oilerListBean"))

        binding.cancel.setOnSingleClickListener { dismiss() }
    }

    override fun getDialogHeight() = WindowManager.LayoutParams.WRAP_CONTENT

    override fun getDialogWidth() = WindowManager.LayoutParams.WRAP_CONTENT
}