package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_choose_body.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.view.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_three.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_two.*

class ChooseBodyActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    var currentCouple = 1
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_body)
        go_to_main_btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        var adapter = ChooseBodySliderAdapter(supportFragmentManager)
        adapter.addFragment(ChooseBodyPicturesFragmentOne())
        adapter.addFragment(ChooseBodyPicturesFragmentTwo())
        adapter.addFragment(ChooseBodyPicturesFragmentThree())
        choose_body_view_pager.adapter = adapter
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        lateinit var weight: String //
        lateinit var height: String //
        if (intent.extras != null) {
            weight = intent.extras.getInt("weight").toString()
            height = intent.extras.getInt("height").toString()
            preferences
                .edit()
                //.putString("weight", weight_text.text.toString())
                //.putString("height", height_text.text.toString())
                .apply()
        } else {

        }
        slider1_btn.setOnClickListener {
            choose_body_view_pager.currentItem = 0
        }
        slider2_btn.setOnClickListener {
            choose_body_view_pager.currentItem = 1
        }
        slider3_btn.setOnClickListener {
            choose_body_view_pager.currentItem = 2
        }

        // todo: SET ONCLICKLISTENERS FOR 12 PICTURES ON SLIDES
    }
}
