package com.example.projectapplication

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_training_info.*

class TrainingInfoActivity : AppCompatActivity() {

    var currentDay = 0;
    lateinit var exercisesArray : ArrayList<Exercise>
    lateinit var mDataBase: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_info)
        mDataBase = DataBase(this)
        currentDay = intent.getIntExtra("day", 0)
        exercisesArray = mDataBase.getTraining(currentDay)
        val titleText = findViewById<TextView>(R.id.workout_info_title_text)
        titleText.text = "День ${intent.getStringExtra("text")}"
        for(i in 0 until exercisesArray.count()){
            addItemToLayout(i)
        }
        info_begin_workout_btn.setOnClickListener {
            startActivity(Intent(this,WorkoutActivity::class.java))
            finish()
        }

        }

    private fun addItemToLayout(current: Int) {
        val mainLayout = findViewById<LinearLayout>(R.id.ex_info_list)
        val layoutToAdd = LinearLayout(applicationContext)
        val exNameText = TextView(applicationContext)
        val exRepeatsCount = TextView(applicationContext)
        var lparams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val exNameTextParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        val exRepeatsCountParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        lparams.setMargins(15,15,15,15)
        exNameTextParams.weight = 4f
        exRepeatsCountParams.weight = 1f
        layoutToAdd.layoutParams = lparams
        exNameText.layoutParams = exNameTextParams
        exRepeatsCount.layoutParams = exRepeatsCountParams
        layoutToAdd.orientation = LinearLayout.HORIZONTAL
        exNameText.text = exercisesArray[current].name
        exRepeatsCount.text = exercisesArray[current].currentCount.toString()
        exNameText.textSize = 20f
        exRepeatsCount.textSize = 20f
        exNameText.typeface = Typeface.create("Franklin",Typeface.BOLD_ITALIC)
        exRepeatsCount.typeface = Typeface.create("Franklin",Typeface.BOLD_ITALIC)
        layoutToAdd.addView(exNameText)
        layoutToAdd.addView(exRepeatsCount)
        mainLayout.addView(layoutToAdd)
    }
}
