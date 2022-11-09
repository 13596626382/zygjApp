package com.llx.common.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.llx.common.R
import com.llx.common.databinding.ViewTitleBarBinding
import com.llx.common.util.setOnBackActivity

class TitleBarView(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {
    private val titleBarView: ViewTitleBarBinding
    var onImageClickListener: (View.()-> Unit)? = null

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView)
        val title = array.getString(R.styleable.TitleBarView_title)
        val image = array.getDrawable(R.styleable.TitleBarView_image)
        array.recycle()
        titleBarView = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_title_bar,
            null,
            false
        )
        titleBarView.title.text = title
        titleBarView.image.setImageDrawable(image)
        titleBarView.image.setOnClickListener {
            onImageClickListener?.invoke(it)
        }

        addView(titleBarView.root)
    }


    fun setOnBack(activity: AppCompatActivity) {
        titleBarView.back.setOnBackActivity(activity)
    }

    fun setTitle(title: String) {
        titleBarView.title.text = title
    }

    fun setImageGone() {
        titleBarView.image.visibility = INVISIBLE
    }
}