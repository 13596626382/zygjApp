package com.llx.common.dialog

import android.view.WindowManager
import com.llx.common.CommonConstant
import com.llx.common.R
import com.llx.common.base.BaseDialogFragment
import com.llx.common.databinding.DialogStringBinding
import com.llx.common.util.argumentsStringArraylist
import com.llx.common.util.bind

class StringDialogFragment : BaseDialogFragment<DialogStringBinding>(R.layout.dialog_string) {
    var onSelectStringResult: ((String, Int) -> Unit)? = null
    override fun init() {
        binding.recyclerView.bind(StringDialogAdapter()) {
            setOnItemClickListener { _, _, position ->
                onSelectStringResult?.invoke(data[position], position)
                dismiss()
            }
            setList(argumentsStringArraylist(CommonConstant.STRING_DIALOG_DATA))
        }
    }

    override fun getDialogWidth() = WindowManager.LayoutParams.MATCH_PARENT
}

