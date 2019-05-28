package com.example.projectapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.TextView

class WorkoutActivity : AppCompatActivity() {

    var exercisesCount = 5
    var currentExercise = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeExerciseLayout()
    }

    private fun makeExerciseLayout() {
        if (currentExercise <= exercisesCount) {
            setContentView(R.layout.activity_training_workout)
            val nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            val currentExerciseNumber = findViewById<TextView>(R.id.ex_num)
            currentExerciseNumber.text = "$currentExercise / $exercisesCount"
            nextLayoutButton.setOnClickListener {
                makePauseLayout()
            }
        }

    }

    private fun makePauseLayout() {
        if (currentExercise < exercisesCount) {
            setContentView(R.layout.activity_training_pause)
            var nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            nextLayoutButton.setOnClickListener {
                currentExercise++
                makeExerciseLayout()
            }
        }

    }
}
