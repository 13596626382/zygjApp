package com.llx.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {
    protected lateinit var binding: DB
    private var isFirst = true
    private var isLoadCompleted = false
    lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this
        mContext = requireContext()
        isFirst = false
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (!isFirst && isLoadCompleted) {
            update()
        }
        if (!isFirst && !isLoadCompleted) {
            initData()
            isLoadCompleted = true
        }
    }


    abstract fun initData()

    protected open fun update() {}
}