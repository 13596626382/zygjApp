package com.llx.common.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

abstract class BaseAdapter1<T : Any, DB : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
    private val BR_ID: Int
) : BaseQuickAdapter<T, BaseDataBindingHolder<DB>>(layoutRes) {

    var onBind: (BaseDataBindingHolder<DB>.() -> Unit)? = null

    override fun convert(holder: BaseDataBindingHolder<DB>, item: T) {
        holder.dataBinding?.setVariable(BR_ID, item)
        holder.dataBinding?.executePendingBindings()
        onBind?.invoke(holder)
    }

    fun onBind(block: BaseDataBindingHolder<DB>.() -> Unit) {
        onBind = block
    }


}


