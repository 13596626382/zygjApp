package com.llx.common.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.llx.common.util.topActivity

@Database(
    entities = [SearchBean::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {
        private const val DB_NAME = "app.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun get(): AppDatabase {
            return instance ?: Room.databaseBuilder(
                topActivity, AppDatabase::class.java,
                DB_NAME
            )
                .build().also {
                    instance = it
                }
        }
    }
}