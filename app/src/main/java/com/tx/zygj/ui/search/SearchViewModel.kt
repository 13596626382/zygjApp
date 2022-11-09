package com.tx.zygj.ui.search

import androidx.lifecycle.MutableLiveData
import com.llx.common.base.BaseRefreshViewModel
import com.llx.common.room.SearchBean
import com.tx.zygj.bean.MemberManageBean

class SearchViewModel : BaseRefreshViewModel<MemberManageBean>() {
    private val repository by lazy { SearchRepository() }

    var content = ""
    override suspend fun loadData(page: Int) = repository.likeMembers(content, page)

    val searchBean = MutableLiveData<List<SearchBean>>()

    fun getSearchHistory() {
        launch {
            searchBean.value = repository.getSearchHistory()
        }
    }

    fun saveSearch() {
        launch {
            if (repository.getRepeatSearchHistory(content).isEmpty()) {
                repository.saveSearch(content)
                getSearchHistory()
            }
        }
    }

    fun deleteSearch() {
        launch {
            repository.deleteSearch()
            getSearchHistory()
        }
    }
}