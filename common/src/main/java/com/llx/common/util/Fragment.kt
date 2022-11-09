package com.llx.common.util

import android.os.Build
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun Fragment.withArguments(vararg pairs: Pair<String, *>) = apply {
    arguments = bundleOf(*pairs)
}

fun Fragment.argumentInt(name: String) = arguments?.argumentInt(name)

fun Fragment.argumentString(name: String) = arguments?.argumentString(name)

fun Fragment.argumentBoolean(name: String) = arguments?.argumentBoolean(name)

inline fun <reified T> Fragment.argumentsParcelableExtras(name: String): T =
    arguments!!.argumentsParcelableExtras(name)

fun Fragment.argumentsStringArraylist(name: String) = arguments?.argumentsStringArraylist(name)

inline fun <reified T : Parcelable> Fragment.argumentsParcelableArrayList(name: String) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments!!.getParcelableArrayList(name, T::class.java)
    } else {
        arguments!!.getParcelableArrayList(name)
    }