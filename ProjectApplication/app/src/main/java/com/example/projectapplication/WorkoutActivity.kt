package com.example.projectapplication

import android.content.DialogInterface
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_training_done.*

class WorkoutActivity : AppCompatActivity() {

    var exercisesCount = 5
    var currentExercise = 1
    var repeatsCount = arrayOf(10, 12, 7, 25, 15)
    var exNamesArray = arrayOf("Приседания", "Отжимания", "Жим лежа 100кг", "Подтягивания", "Еще какая нибудь хуйня")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeExerciseLayout()
    }

    private fun makeExerciseLayout() {
        if (currentExercise <= exercisesCount) {
            setContentView(R.layout.activity_training_workout)
            val nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            val currentExerciseNumber = findViewById<TextView>(R.id.ex_num)
            val exTitleText = findViewById<TextView>(R.id.ex_title)
            currentExerciseNumber.text = "$currentExercise / $exercisesCount"
            exTitleText.text = exNamesArray[currentExercise - 1]
            nextLayoutButton.setOnClickListener {
                makePauseLayout()
            }
        }

    }

    private fun makePauseLayout() {
        if (currentExercise < exercisesCount) {
            setContentView(R.layout.activity_training_pause)
            var nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            var exLayout = findViewById<LinearLayout>(R.id.ex_progress_list)
            nextLayoutButton.setOnClickListener {
                currentExercise++
                makeExerciseLayout()
            }
            for (i in 0 until exercisesCount) {
                val exListItem = TextView(applicationContext)
                exListItem.text = "${repeatsCount[i]} ${exNamesArray[i]}"
                exListItem.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                if (i < currentExercise) {
                    exListItem.paintFlags = exListItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                exLayout.addView(exListItem)
            }
        } else {
            makeDoneLayout()
        }

    }

    private fun makeDoneLayout() {
        setContentView(R.layout.activity_training_done)
        btn_next_layout.setOnClickListener {
            finish()
        }
    }

    private fun makeQuitAlertDialog() {
        val quitDialog = AlertDialog.Builder(this)
        quitDialog.setTitle("Вы точно хотите выйти?")
        quitDialog.setPositiveButton("Да", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                finish()
            }

        })
        quitDialog.setNeutralButton("Нет", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })
        quitDialog.create().show()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        makeQuitAlertDialog()

    }
}
