package com.llx.common.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.llx.common.R


fun ImageView.loadUrl(url: String?) {
    Glide.with(this).load(url).error(R.mipmap.ic_launcher).into(this)
}

fun ImageView.loadCircleUrl(url: String?) {
    Glide.with(this).load(url).error(R.mipmap.ic_launcher).apply(
        RequestOptions.bitmapTransform(CircleCrop())
    ).into(this)
}


fun ImageView.loadUrl(path: Int?) {
    Glide.with(this).load(path).error(R.mipmap.ic_launcher).into(this)
}