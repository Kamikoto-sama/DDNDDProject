package com.example.projectapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FirstLaunchSliderAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager){
    private val fragmentList : MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }


    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }

}