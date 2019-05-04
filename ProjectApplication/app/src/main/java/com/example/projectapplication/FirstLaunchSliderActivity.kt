package com.example.projectapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_first_launch_slider.*
import kotlinx.android.synthetic.main.fragment_first_launch_slide_three.*

class FirstLaunchSliderActivity : AppCompatActivity() {

    lateinit var adapter: FirstLaunchSliderAdapter
    lateinit var activity: Activity
    lateinit var preferences: SharedPreferences
    val pref_show_intro = "Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_launch_slider)
        activity = this
        makeAdapter()
        preferences = getSharedPreferences("IntroSlider",Context.MODE_PRIVATE)
        if(!preferences.getBoolean(pref_show_intro,true)){
            startActivity(Intent(activity,ChooseBodyActivity::class.java))
            finish()
        }
        intro_view_pager.adapter = adapter

        intro_btn_next.setOnClickListener {
            intro_view_pager.currentItem++
        }

        intro_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                if(p0==0){
                    intro_btn_back.visibility = ViewPager.INVISIBLE
                } else{
                    intro_btn_back.visibility = ViewPager.VISIBLE
                    intro_btn_back.setOnClickListener{
                        intro_view_pager.currentItem--
                    }
                }
                if (p0 == adapter.count - 1) {
                    intro_btn_next.text = "Готово"
                    intro_btn_next.setOnClickListener {
                        var introIntent = Intent(activity, ChooseBodyActivity::class.java)
                        introIntent.putExtra("height",enter_height.value.toString().toInt())
                        introIntent.putExtra("weight",enter_weight.value.toString().toInt())
                        startActivity(introIntent)
                        finish()
                        preferences
                            .edit()
                            .putBoolean(pref_show_intro, false)
                            .apply()
                    }
                } else {
                    intro_btn_next.text = "далее"
                    intro_btn_next.setOnClickListener {
                        intro_view_pager.currentItem++
                    }
                }
                when(intro_view_pager.currentItem){
                    0->{
                        intro_indicator1.setTextColor(Color.BLACK)
                        intro_indicator2.setTextColor(Color.GRAY)
                        intro_indicator3.setTextColor(Color.GRAY)
                    }
                    1->{
                        intro_indicator1.setTextColor(Color.GRAY)
                        intro_indicator2.setTextColor(Color.BLACK)
                        intro_indicator3.setTextColor(Color.GRAY)
                    }
                    2->{
                        intro_indicator1.setTextColor(Color.GRAY)
                        intro_indicator2.setTextColor(Color.GRAY)
                        intro_indicator3.setTextColor(Color.BLACK)
                    }
                }
            }

        })

    }

    fun makeAdapter(){
        adapter = FirstLaunchSliderAdapter(supportFragmentManager)
        adapter.addFragment(FirstLaunchSlideOne())
        adapter.addFragment(FirstLaunchSlideTwo())
        adapter.addFragment(FirstLaunchSlideThree())
    }


}
