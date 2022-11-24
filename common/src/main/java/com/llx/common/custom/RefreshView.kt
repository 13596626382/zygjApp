package com.llx.common.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.llx.common.R
import com.llx.common.base.BaseAdapter
import com.llx.common.base.BaseRefreshViewModel
import com.llx.common.databinding.RefreshViewBinding
import com.llx.common.util.bind
import com.llx.common.util.setOnSingleClickListener

class RefreshView<T>(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {
    private var binding: RefreshViewBinding
    private lateinit var mViewModel: BaseRefreshViewModel<T>
    private lateinit var mAdapter: BaseAdapter<T, *>
    private lateinit var mLifecycleOwner: LifecycleOwner
    private var mPageCount = 1
    private var mDefaultPageCount = 1

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.refresh_view,
            null,
            false
        )
        addView(binding.root)
        binding.smartRefreshLayout.setOnRefreshListener {
            mPageCount = mDefaultPageCount
            mViewModel.openLoadData(mPageCount)
        }
        binding.smartRefreshLayout.setOnLoadMoreListener {
            mPageCount++
            mViewModel.openLoadData(mPageCount)
        }
        binding.noNetwork.setOnSingleClickListener {
            mPageCount = mDefaultPageCount
            binding.noNetwork.visibility = View.GONE
            binding.progressBae.visibility = View.VISIBLE
            mViewModel.openLoadData(mPageCount)
        }
    }

    fun setViewModel(viewModel: BaseRefreshViewModel<T>) {
        mViewModel = viewModel
    }

    fun setAdapter(mAdapter: BaseAdapter<T, *>) {
        this.mAdapter = mAdapter
        binding.recyclerView.bind(mAdapter) { }
    }


    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        mLifecycleOwner = lifecycleOwner

        mViewModel.data.observe(mLifecycleOwner) {
            if (mPageCount == mDefaultPageCount) {
                mAdapter.setList(it.data)
            } else {
                mAdapter.addData(it.data)
            }
            loadResult(it.pageCurrent <= it.pageCount)
        }
        mViewModel.noNetwork.observe(lifecycleOwner) {
            binding.noNetwork.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            binding.progressBae.visibility = View.GONE
        }
    }

    fun loaData() {
        mPageCount = mDefaultPageCount
        mViewModel.openLoadData(mPageCount)
    }

    private fun loadResult(b: Boolean) {
        binding.smartRefreshLayout.finishRefresh()//结束刷新
        binding.smartRefreshLayout.finishLoadMore()//结束加载
        binding.recyclerView.visibility = VISIBLE
        binding.progressBae.visibility = GONE
        binding.noNetwork.visibility = View.GONE
        if (b) {
            binding.smartRefreshLayout.finishLoadMoreWithNoMoreData()
        }
    }
}