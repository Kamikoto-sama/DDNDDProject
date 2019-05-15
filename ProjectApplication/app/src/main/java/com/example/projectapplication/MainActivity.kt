package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.brkckr.circularprogressbar.CircularProgressBar
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userPreferences: SharedPreferences
    var height: Int = 0
    var weight: Int = 0
    lateinit var bodyType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userPreferences = getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
        if (userPreferences.getBoolean("IS_FIRST_LAUNCH", true)) {
            height = intent.extras.getInt("height")
            weight = intent.extras.getInt("weight")
            bodyType = intent.extras.getString("bodyType")
            userPreferences.edit()
                .putInt("height", height)
                .putInt("weight", weight)
                .putString("bodyType", bodyType)
                .putBoolean("IS_FIRST_LAUNCH", false)
                .apply()
        } else {
            height = userPreferences.getInt("height", 0)
            weight = userPreferences.getInt("weight", 0)
            bodyType = userPreferences.getString("bodyType", null)
        }
        val bar = findViewById<CircularProgressBar>(R.id.progress_exercises_bar)
        val akbar = findViewById<CircularProgressBar>(R.id.progress_days_bar)
        fuckfuckfuck.selectValue(50)
        fuckfuckfuckfuck.selectValue(70)
        fuckfuckfuck.setValuePickerListener(object : RulerValuePickerListener{
            override fun onIntermediateValueChange(selectedValue: Int) {
                bar.progressValue =  fuckfuckfuck.currentValue.toFloat()
                progress_exercises_bar_text.text = "${fuckfuckfuck.currentValue} %"
            }

            override fun onValueChange(selectedValue: Int) {

            }

        })
        fuckfuckfuckfuck.setValuePickerListener(object : RulerValuePickerListener{
            override fun onValueChange(selectedValue: Int) {

            }

            override fun onIntermediateValueChange(selectedValue: Int) {
                akbar.progressValue =  fuckfuckfuckfuck.currentValue.toFloat()
                progress_days_bar_text.text = "${fuckfuckfuckfuck.currentValue} %"
            }

        })
    }

}
