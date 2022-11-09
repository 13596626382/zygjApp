package com.llx.common.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchBean(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: String
)