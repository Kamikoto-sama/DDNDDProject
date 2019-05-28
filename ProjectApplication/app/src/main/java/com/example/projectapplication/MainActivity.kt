package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        val workoutBtnsArray : Array<Button> = arrayOf(
            findViewById(R.id.workout_btn_1),
            findViewById(R.id.workout_btn_2),
            findViewById(R.id.workout_btn_3),
            findViewById(R.id.workout_btn_4),
            findViewById(R.id.workout_btn_5),
            findViewById(R.id.workout_btn_6),
            findViewById(R.id.workout_btn_7),
            findViewById(R.id.workout_btn_8),
            findViewById(R.id.workout_btn_9),
            findViewById(R.id.workout_btn_10),
            findViewById(R.id.workout_btn_11),
            findViewById(R.id.workout_btn_12),
            findViewById(R.id.workout_btn_13),
            findViewById(R.id.workout_btn_14),
            findViewById(R.id.workout_btn_15),
            findViewById(R.id.workout_btn_16),
            findViewById(R.id.workout_btn_17),
            findViewById(R.id.workout_btn_18),
            findViewById(R.id.workout_btn_19),
            findViewById(R.id.workout_btn_20),
            findViewById(R.id.workout_btn_21),
            findViewById(R.id.workout_btn_22),
            findViewById(R.id.workout_btn_23),
            findViewById(R.id.workout_btn_24),
            findViewById(R.id.workout_btn_25),
            findViewById(R.id.workout_btn_26),
            findViewById(R.id.workout_btn_27),
            findViewById(R.id.workout_btn_28),
            findViewById(R.id.workout_btn_29),
            findViewById(R.id.workout_btn_30))

        for (i in 0..workoutBtnsArray.lastIndex){
        workoutBtnsArray[i].setOnClickListener {
            startActivity(Intent(this,TrainingInfoActivity::class.java).putExtra("text",workoutBtnsArray[i].text))
        }
        }
        start_workout_btn.setOnClickListener {
            startActivity(Intent(this,WorkoutActivity::class.java))
        }
    }



}
