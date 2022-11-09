package com.llx.common.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.llx.common.base.BaseAdapter


inline fun <T, reified D : BaseAdapter<T, *>> RecyclerView.bind(
    mAdapter: D,
    crossinline block: D.() -> Unit
): D {
    layoutManager = LinearLayoutManager(context)
    adapter = mAdapter
    block.invoke(mAdapter)
    return mAdapter
}


inline fun <T, reified D : BaseAdapter<T, *>> RecyclerView.bindHorizontal(
    mAdapter: D,
    crossinline block: D.() -> Unit
): D {
    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    adapter = mAdapter
    block.invoke(mAdapter)
    return mAdapter
}



inline fun <T, reified D : BaseAdapter<T, *>> RecyclerView.bindGrid(
    mAdapter: D,
    spanCount: Int,
    crossinline block: D.() -> Unit
): D {
    layoutManager = GridLayoutManager(context, spanCount)
    adapter = mAdapter
    block.invoke(mAdapter)
    return mAdapter
}
