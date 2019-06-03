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
    lateinit var weight: String //
    lateinit var height: String //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_body)
        intentToSend = Intent(this, MainActivity::class.java)
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)

        doNotLaunchIfNeeded(preferences)
        initializeBodySliderAdapter()
        setListenerForGoToMainButton()
        setListenersForSlideButtons()
        defineBodyType()


    }

    private fun defineBodyType() {
        if (intent.extras != null) {
            weight = intent.extras.getInt("weight").toString()
            height = intent.extras.getInt("height").toString()
            countBodyTypeByFormula(height.toDouble(), weight.toInt())
            preferences
                .edit()
                .putString("weight", weight)
                .putString("height", height)
                .apply()
        } else {
            weight = preferences.getString("weight", "0").toString()
            height = preferences.getString("height", "0").toString()
            countBodyTypeByFormula(height.toDouble(), weight.toInt())
        }
    }

    private fun doNotLaunchIfNeeded(preferences: SharedPreferences) {
        if (!preferences.getBoolean("isNeedToLaunch", true)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setListenerForGoToMainButton() {
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
    }

    private fun initializeBodySliderAdapter(): ChooseBodySliderAdapter {
        var adapter = ChooseBodySliderAdapter(supportFragmentManager)
        adapter.addFragment(ChooseBodyPicturesFragmentOne())
        adapter.addFragment(ChooseBodyPicturesFragmentTwo())
        adapter.addFragment(ChooseBodyPicturesFragmentThree())
        choose_body_view_pager.adapter = adapter
        return adapter
    }

    private fun setListenersForSlideButtons() {
        slider1_btn.setOnClickListener {
            pictureClickAnimation(slider1_btn, this)
            choose_body_view_pager.currentItem = 0
        }
        slider2_btn.setOnClickListener {
            pictureClickAnimation(slider2_btn, this)
            choose_body_view_pager.currentItem = 1
        }
        slider3_btn.setOnClickListener {
            pictureClickAnimation(slider3_btn, this)
            choose_body_view_pager.currentItem = 2
        }
    }

    private fun countBodyTypeByFormula(height: Double, weight: Int) {
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
        nick_fury_text.text = "Лучше выбрать: $bodyType"
    }

}
