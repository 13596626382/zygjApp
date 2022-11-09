package com.llx.common.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

fun getBitmap(string: String): Bitmap? {
    val options = BitmapFactory.Options()
    options.inTargetDensity = 160
    options.inDensity = 160
    return BitmapFactory.decodeFile(File(string).path, options)
}
