package com.llx.common.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.llx.common.R

abstract class BaseDialogFragment<DB : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
    DialogFragment() {
    protected lateinit var binding: DB
    protected lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutRes, null, false)
        binding.lifecycleOwner = this
        dialog?.setCanceledOnTouchOutside(getCanceled())
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        mContext = requireContext()
        init()
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.BottomDialog)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
        params?.dimAmount = getDimAmount()
        params?.height = getDialogHeight()
        params?.width = getDialogWidth()
        params?.gravity = getDialogGravity()
        window?.attributes = params
    }

    abstract fun init()

    open fun getCanceled() = true

    open fun getDimAmount() = 0.5f

    open fun getDialogHeight() = WindowManager.LayoutParams.WRAP_CONTENT

    open fun getDialogWidth() = WindowManager.LayoutParams.MATCH_PARENT

    open fun getDialogGravity() = Gravity.BOTTOM

}