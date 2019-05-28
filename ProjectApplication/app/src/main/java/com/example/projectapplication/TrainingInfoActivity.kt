package com.example.projectapplication

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_training_info.*

class TrainingInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_info)
        info_begin_workout_btn.setOnClickListener {
            startActivity(Intent(this,WorkoutActivity::class.java))
            finish()
        }
        }
    }
