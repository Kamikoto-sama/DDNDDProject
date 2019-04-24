package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userPreferences: SharedPreferences
    var height : Int = 0
    var weight : Int = 0
    lateinit var bodyType : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userPreferences = getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
        if(userPreferences.getBoolean("IS_FIRST_LAUNCH", true)){
            height = intent.extras.getInt("height")
            weight = intent.extras.getInt("weight")
            bodyType = intent.extras.getString("bodyType")
            userPreferences.edit()
                .putInt("height",height)
                .putInt("weight",weight)
                .putString("bodyType", bodyType)
                .putBoolean("IS_FIRST_LAUNCH", false)
                .apply()
        } else{
            height = userPreferences.getInt("height", 0)
            weight = userPreferences.getInt("weight", 0)
            bodyType = userPreferences.getString("bodyType",null)
        }

        height_text.text = "Рост: $height см"
        weight_text.text = "Вес: $weight кг"
        body_text.text = bodyType

        body_picture.setImageResource(when(bodyType){
        "body1" -> R.drawable.choose_body_picture1
        "body2" -> R.drawable.choose_body_picture2
        "body3" -> R.drawable.choose_body_picture3
        "body4" -> R.drawable.choose_body_picture4
        "body5" -> R.drawable.choose_body_picture5
        "body6" -> R.drawable.choose_body_picture6
        "body7" -> R.drawable.choose_body_picture7
        "body8" -> R.drawable.choose_body_picture8
        "body9" -> R.drawable.choose_body_picture9
        "body10" -> R.drawable.choose_body_picture10
        "body11" -> R.drawable.choose_body_picture11
        "body12" -> R.drawable.choose_body_picture12
            else -> 0
        })

    }
}
