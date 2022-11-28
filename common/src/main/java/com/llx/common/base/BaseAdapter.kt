package com.llx.common.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

abstract class BaseAdapter<T, DB : ViewDataBinding>(
    @LayoutRes layoutRes: Int,
    private val BR_ID: Int
) :
    BaseQuickAdapter<T, BaseDataBindingHolder<DB>>(layoutRes) {

    var onBind: (BaseDataBindingHolder<DB>.() -> Unit)? = null

    override fun convert(holder: BaseDataBindingHolder<DB>, item: T) {
        holder.dataBinding?.setVariable(BR_ID, item)
        holder.dataBinding?.executePendingBindings()
        convert1(holder.dataBinding!!, item, holder.layoutPosition)
        convert2(holder, holder.dataBinding!!, item, holder.layoutPosition)
        onBind?.invoke(holder)

    }

    open fun convert1(binding: DB, item: T, position: Int) {}
    open fun convert2(holder: BaseDataBindingHolder<DB>, binding: DB, item: T, position: Int) {}

    /**
     * 在convert外部调用holder
     * adapter.onBind {
     *
     * }
     */
    fun onBind(block: BaseDataBindingHolder<DB>.() -> Unit) {
        onBind = block
    }
}