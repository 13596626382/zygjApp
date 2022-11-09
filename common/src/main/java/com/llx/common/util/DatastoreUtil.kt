@file:Suppress("UNCHECKED_CAST")

package com.llx.common.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "zygj")

//读取
suspend fun <T> readContent(key: Preferences.Key<T>) = topActivity.readContent(key)

//读取
suspend fun <T> Context.readContent(key: Preferences.Key<T>): T = dataStore.data.map {
    it[key] ?: ""
}.first() as T

//写入
suspend fun writeContent(key: Preferences.Key<String>, value: String) =
    topActivity.writeContent(key, value)

//写入
suspend fun Context.writeContent(key: Preferences.Key<String>, value: String) {
    dataStore.edit {
        it[key] = value
    }
}

object DatastoreUtil {
    val USER_INFO = stringPreferencesKey("user_info")
    val PRINT_SETTING = stringPreferencesKey("print_setting")
}

