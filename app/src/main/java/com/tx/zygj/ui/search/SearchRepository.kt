package com.tx.zygj.ui.search

import com.llx.common.room.AppDatabase
import com.llx.common.room.SearchBean
import com.tx.zygj.api.BaseRepository

class SearchRepository : BaseRepository() {

    suspend fun likeMembers(like: String, page: Int) =
        retrofit.likeMembers(like, page)

    suspend fun getSearchHistory() = AppDatabase.get().searchDao().getSearchHistory()

    suspend fun getRepeatSearchHistory(content: String) =
        AppDatabase.get().searchDao().getRepeatSearchHistory(content)

    suspend fun saveSearch(content: String) =
        AppDatabase.get().searchDao().insertSearch(SearchBean(content = content))

    suspend fun deleteSearch() = AppDatabase.get().searchDao().deleteSearch()
}