package com.llx.common.custom

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.llx.common.base.BaseActivity

class ViewPagerAdapter(activity: BaseActivity<*>, private val fragment: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount() = fragment.size

    override fun createFragment(position: Int) = fragment[position]

}