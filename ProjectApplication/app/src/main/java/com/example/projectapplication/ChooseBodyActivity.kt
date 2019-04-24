package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choose_body.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.view.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_three.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_two.*

class ChooseBodyActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    lateinit var bodyType: String
    lateinit var intentToSend: Intent
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_body)
        intentToSend = Intent(this, MainActivity::class.java)
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        if (!preferences.getBoolean("isNeedToLaunch", true)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        go_to_main_btn.setOnClickListener {
            if (intent.getStringExtra("bodyType") != null) {
                intentToSend.putExtra("bodyType", intent.getStringExtra("bodyType"))

                intentToSend.putExtra("weight", intent.extras.getInt("weight"))
                intentToSend.putExtra("height", intent.extras.getInt("height"))
                preferences.edit().putBoolean("isNeedToLaunch", false).apply()
                startActivity(intentToSend)
                finish()
            } else {
                Toast.makeText(this, "Выбери себе тело чтобы продолжить", Toast.LENGTH_SHORT).show()
            }
        }
        var adapter = ChooseBodySliderAdapter(supportFragmentManager)
        adapter.addFragment(ChooseBodyPicturesFragmentOne())
        adapter.addFragment(ChooseBodyPicturesFragmentTwo())
        adapter.addFragment(ChooseBodyPicturesFragmentThree())
        choose_body_view_pager.adapter = adapter
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

    }


    fun defineBodyType(height: Int, weight: Int): String {
        // todo определить тип тела исходя из веса и роста(хз как пока что) и запихуить его в интент для главной активити
        return ""
    }

}
