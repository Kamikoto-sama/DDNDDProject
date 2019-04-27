package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        body_text.text = bodyType

        body_picture.setImageResource(
            when (bodyType) {
                "Эктоморф 1" -> R.drawable.choose_body_picture1
                "Эктоморф 2" -> R.drawable.choose_body_picture2
                "Эктоморф 3" -> R.drawable.choose_body_picture3
                "Эктоморф 4" -> R.drawable.choose_body_picture4
                "Мезоморф 1" -> R.drawable.choose_body_picture5
                "Мезоморф 2" -> R.drawable.choose_body_picture6
                "Мезоморф 3" -> R.drawable.choose_body_picture7
                "Мезоморф 4" -> R.drawable.choose_body_picture8
                "Эндоморф 1" -> R.drawable.choose_body_picture9
                "Эндоморф 2" -> R.drawable.choose_body_picture10
                "Эндоморф 3" -> R.drawable.choose_body_picture11
                "Эндоморф 4" -> R.drawable.choose_body_picture12
                else -> 0
            }
        )

        workout_btn1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(
                    Intent(this@MainActivity, TrainingActivity::class.java).putExtra(
                        "some_variable",
                        "Это тренировка рук"
                    )
                )
            }

        })
        workout_btn2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(
                    Intent(this@MainActivity, TrainingActivity::class.java).putExtra(
                        "some_variable",
                        "Это тренировка груди"
                    )
                )
            }

        })
        workout_btn3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(
                    Intent(this@MainActivity, TrainingActivity::class.java).putExtra(
                        "some_variable",
                        "Это тренировка ног"
                    )
                )
            }

        })
        workout_btn4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(
                    Intent(this@MainActivity, TrainingActivity::class.java).putExtra(
                        "some_variable",
                        "Это тренировка еще какой нибудь хуйни"
                    )
                )
            }

        })
    }

}
