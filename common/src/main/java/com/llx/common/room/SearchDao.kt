package com.llx.common.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SearchDao {

    /**
     * 插入搜索内容
     */
    @Insert
    suspend fun insertSearch(searchBean: SearchBean)

    /**
     * 查询历史搜索列表
     */
    @Query("SELECT * FROM SearchBean")
    suspend fun getSearchHistory(): List<SearchBean>

    /**
     * 检查是否有重复的数据
     */
    @Query("SELECT content FROM SearchBean WHERE content= :content")
    suspend fun getRepeatSearchHistory(content: String): List<String>

    /**
     * 清空搜索内容
     */
    @Query("DELETE FROM SearchBean")
    suspend fun deleteSearch()
}