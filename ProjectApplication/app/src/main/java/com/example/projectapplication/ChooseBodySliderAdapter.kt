package com.example.projectapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter

class ChooseBodySliderAdapter(manager:FragmentManager) : FragmentPagerAdapter(manager){
    private val fragmentList : MutableList<Fragment> = ArrayList()

    override fun getItem(p0: Int): Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }

}