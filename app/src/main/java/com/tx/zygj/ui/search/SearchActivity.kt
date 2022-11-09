package com.tx.zygj.ui.search

import android.content.Intent
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import com.llx.common.CommonConstant
import com.llx.common.base.BaseActivity
import com.llx.common.util.*
import com.tx.zygj.R
import com.tx.zygj.databinding.ActivitySearchBinding
import com.tx.zygj.ui.member.information.MemberInformationActivity

/**
 * 搜搜会员
 */
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val mAdapter by lazy { SearchAdapter() }
    private val model: SearchViewModel by viewModels()
    override fun initData() {
        binding.refreshView.apply {
            setViewModel(model)
            setAdapter(mAdapter)
            setLifecycleOwner(this@SearchActivity)
        }
        mAdapter.setEmptyView(R.layout.view_empty)
        mAdapter.setOnItemClickListener { _, _, position ->
            if (intentBooleanExtras(CommonConstant.SEARCH_TYPE)) {
                val intent = Intent()
                intent.putExtra(CommonConstant.MEMBER_BEAN, mAdapter.data[position])
                setResult(CommonConstant.REQUEST_CODE, intent)
                onBack()
            } else {
                startActivity<MemberInformationActivity>(CommonConstant.MEMBER_ID to mAdapter.data[position].id)
            }
        }
        binding.search.setOnSingleClickListener {
            val like = binding.editText.textString()
            if (like == "") {
                toast("请输入搜索内容")
                return@setOnSingleClickListener
            }
            search(like)
        }
        binding.back.setOnClickListener {
            if (binding.searchHistory.visibility == View.GONE) {
                binding.searchHistory.visibility = View.VISIBLE
                binding.refreshView.visibility = View.GONE
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        binding.delete.setOnSingleClickListener {
            model.deleteSearch()
        }
        binding.editText.setOnEditorActionListener { _, _, event ->
            if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                val like = binding.editText.textString()
                if (like == "") {
                    toast("请输入搜索内容")

                } else {
                    search(like)
                }
            }
            event.keyCode == KeyEvent.KEYCODE_ENTER
        }

        model.getSearchHistory()

        model.searchBean.observe(this) {
            binding.flowLayout.setViews(it) { it1 ->
                search(it1)
                binding.editText.setText(it1)
                binding.editText.setSelection(it1.length)
            }

        }


    }


    private fun search(content: String) {
        model.content = content
        model.saveSearch()
        binding.searchHistory.visibility = View.GONE
        binding.refreshView.visibility = View.VISIBLE
        binding.refreshView.loaData()
    }
}