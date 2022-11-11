package com.llx.common.util

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.llx.common.R

@BindingAdapter("setImage")
fun setImage(view: ImageView, url: String?) {
    view.loadUrl(url)
}

@BindingAdapter("setCircleImage")
fun setCircleImage(view: ImageView, url: String?) {
    view.loadCircleUrl(url)
}

@BindingAdapter("setCardType")
fun setCardType(view: TextView, cardType: Int) {
    view.text = when (cardType) {
        0 -> "汽油卡"
        1 -> "柴油卡"
        2 -> "通用钱包"
        else -> "天然气卡"
    }
}

@BindingAdapter("setMemberAvatarBox")
fun setMemberAvatarBox(view: ImageView, memberType: Int) {
    view.setImageDrawable(
        when (memberType) {
            1 -> getCompatDrawable(R.drawable.icon_member_level_avatar_ag)
            2 -> getCompatDrawable(R.drawable.icon_member_level_avatar_au)
            3 -> getCompatDrawable(R.drawable.icon_member_level_avatar_pt)
            else -> getCompatDrawable(R.drawable.icon_member_level_avatar_supreme)
        }
    )
}

@BindingAdapter("setMemberBg")
fun setMemberBg(view: ConstraintLayout, memberType: Int) {
    view.background = when (memberType) {
        1 -> getCompatDrawable(R.drawable.bg_member_level_ag)
        2 -> getCompatDrawable(R.drawable.bg_member_level_au)
        3 -> getCompatDrawable(R.drawable.bg_member_level_pt)
        else -> getCompatDrawable(R.drawable.bg_member_level_supreme)
    }
}

@BindingAdapter("setMemberBg2")
fun setMemberBg2(view: ConstraintLayout, memberType: Int) {
    view.background = when (memberType) {
        1 -> getCompatDrawable(R.drawable.bg_member_level_ag_2)
        2 -> getCompatDrawable(R.drawable.bg_member_level_au_2)
        3 -> getCompatDrawable(R.drawable.bg_member_level_pt_2)
        else -> getCompatDrawable(R.drawable.bg_member_level_supreme_2)
    }
}

@BindingAdapter("setMemberIcon")
fun setMemberIcon(view: ImageView, memberType: Int) {
    view.setImageDrawable(
        when (memberType) {
            1 -> getCompatDrawable(R.drawable.icon_member_level_ag)
            2 -> getCompatDrawable(R.drawable.icon_member_level_au)
            3 -> getCompatDrawable(R.drawable.icon_member_level_pt)
            else -> getCompatDrawable(R.drawable.icon_member_level_supreme)
        }
    )
}


@BindingAdapter("setMemberText")
fun setMemberText(view: TextView, memberType: Int) {
    view.text = when (memberType) {
        1 -> "白银会员"
        2 -> "黄金会员"
        3 -> "白金会员"
        4 -> "至尊会员"
        else -> "选择会员等级"
    }
}
