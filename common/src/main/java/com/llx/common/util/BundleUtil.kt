package com.llx.common.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable


fun Bundle.argumentInt(name: String) = getInt(name, 0)

fun Bundle.argumentString(name: String) = getString(name)

fun Bundle.argumentBoolean(name: String) = getBoolean(name, false)


inline fun <reified T> Bundle.argumentsParcelableExtras(name: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(name, T::class.java)
    } else {
        getParcelable(name)
    } as T


fun Bundle.argumentsStringArraylist(name: String) = getStringArrayList(name)

inline fun <reified T : Parcelable> Bundle.argumentsParcelableArrayList(name: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayList(name, T::class.java)
    } else {
        getParcelableArrayList(name)
    } as T


