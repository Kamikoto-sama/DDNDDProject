package com.example.projectapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_training_program.*

class TrainingProgramActivity : AppCompatActivity() {
    lateinit var userPreferences: SharedPreferences
    lateinit var workoutBtnsArray: Array<Button>
    lateinit var mDataBase: DataBase
    lateinit var bodyType: String
    var currentDay = 0
    var daysProgress = 0
    var exercisesProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBase = DataBase(this)
        currentDay = mDataBase.getProgress().doneDaysCount
        userPreferences = getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_training_program)
        getUserParameters()
        setButtonsListeners()
        setProgressBarsAndCompletedDays()
    }

    private fun getUserParameters() {
        if (intent.getStringExtra("bodyType") != null)
            bodyType = intent.getStringExtra(("bodyType"))
        else bodyType = userPreferences.getString("bodyType", "undefined")
        userPreferences.edit().putString("bodyType", bodyType).apply()

    }

    private fun setButtonsListeners() {
        workoutBtnsArray = arrayOf(
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
            findViewById(R.id.workout_btn_30)
        )
        for (i in 0..workoutBtnsArray.lastIndex) {
            if (i < currentDay) workoutBtnsArray[i].setBackgroundResource(R.drawable.choose_workout_button_filled)
             workoutBtnsArray[i].setOnClickListener {
                 if (i == currentDay)
                startActivity(
                    Intent(this, TrainingInfoActivity::class.java).putExtra(
                        "text",
                        workoutBtnsArray[i].text
                    ).putExtra("day", i)
                )
            }
            val mLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            mLayoutParams.weight = 1f
            mLayoutParams.setMargins(11, 11, 11, 11)
            workoutBtnsArray[i].layoutParams = mLayoutParams
            start_workout_btn.setOnClickListener {
                startActivity(Intent(this, WorkoutActivity::class.java).putExtra("day", i))
            }
        }

    }

    override fun onResume() {
        super.onResume()
        setProgressBarsAndCompletedDays()

    }

    private fun setProgressBarsAndCompletedDays() {
        daysProgress = mDataBase.getProgress().daysProgress
        exercisesProgress = mDataBase.getProgress().exercisesProgress
        progress_days_bar.progressValue = daysProgress.toFloat()
        progress_exercises_bar.progressValue = exercisesProgress.toFloat()
        progress_days_bar_text.text = "$daysProgress %"
        progress_exercises_bar_text.text = "$exercisesProgress %"
        currentDay = mDataBase.getProgress().doneDaysCount
        for (i in 0 until currentDay) workoutBtnsArray[i].setBackgroundResource(R.drawable.choose_workout_button_filled)
    }
}
