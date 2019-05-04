package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.projectapplication.AnimationAdapter.Companion.pictureClickAnimation
import kotlinx.android.synthetic.main.activity_choose_body.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.view.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_three.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_two.*

class ChooseBodyActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    lateinit var intentToSend: Intent

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
            defineBodyType(height.toDouble(), weight.toInt())
            preferences
                .edit()
                .putString("weight", weight)
                .putString("height", height)
                .apply()
        } else {
            weight = preferences.getString("weight", "0").toString()
            height = preferences.getString("height", "0").toString()
            defineBodyType(height.toDouble(), weight.toInt())
        }
        slider1_btn.setOnClickListener {
            choose_body_view_pager.currentItem = 0
            pictureClickAnimation(slider1_btn, this)
        }
        slider2_btn.setOnClickListener {
            choose_body_view_pager.currentItem = 1
            pictureClickAnimation(slider2_btn, this)
        }
        slider3_btn.setOnClickListener {
            choose_body_view_pager.currentItem = 2
            pictureClickAnimation(slider3_btn, this)
        }

    }


    private fun defineBodyType(height: Double, weight: Int) {
        val metersHeight: Double = height / 100
        val formulaResult: Double = (weight / metersHeight / metersHeight)
        lateinit var bodyType: String
        when {
            formulaResult < 18.5 -> {
                bodyType = "Эктоморф"
                choose_body_view_pager.currentItem = 0
            }
            formulaResult in 18.5..24.9 -> {
                bodyType = "Мезоморф"
                choose_body_view_pager.currentItem = 1
            }
            formulaResult > 25 -> {
                bodyType = "Эндоморф"
                choose_body_view_pager.currentItem = 2
            }
        }
        chosen_body_label.text = "Рекомендуемый тип: $bodyType"
    }

}
