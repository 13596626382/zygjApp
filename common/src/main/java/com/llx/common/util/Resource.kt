@file:Suppress("unused")

package com.llx.common.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

fun getCompatString(@StringRes id: Int): String = topActivity.getCompatString(id)

fun View.getCompatString(@StringRes id: Int): String =
    context.getCompatString(id)

fun Fragment.getCompatString(@StringRes id: Int): String =
    requireActivity().getCompatString(id)

fun Context.getCompatString(@StringRes id: Int): String = getString(id)

@ColorInt
fun Fragment.getCompatColor(@ColorRes id: Int): Int =
    requireContext().getCompatColor(id)

@ColorInt
fun View.getCompatColor(@ColorRes id: Int): Int =
    context.getCompatColor(id)

@ColorInt
fun getCompatColor(@ColorRes id: Int): Int = topActivity.getCompatColor(id)

@ColorInt
fun Context.getCompatColor(@ColorRes id: Int): Int =
    ResourcesCompat.getColor(resources, id, null)


fun Fragment.getCompatDrawable(@DrawableRes id: Int): Drawable? =
    requireContext().getCompatDrawable(id)

fun View.getCompatDrawable(@DrawableRes id: Int): Drawable? =
    context.getCompatDrawable(id)

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? =
    ResourcesCompat.getDrawable(resources, id, null)


fun getCompatDrawable(@DrawableRes id: Int): Drawable? = topActivity.getCompatDrawable(id)

