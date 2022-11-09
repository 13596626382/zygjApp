package com.llx.common.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//json转object
inline fun <reified T> String.fromJson(): T = Gson().fromJson(this, T::class.java)

//json转list
inline fun <reified T> String.fromJsonList(): T =
    Gson().fromJson(this, object : TypeToken<ArrayList<T>>() {}.type)

//bean转json
fun <T> T.toJson(): String = Gson().toJson(this)