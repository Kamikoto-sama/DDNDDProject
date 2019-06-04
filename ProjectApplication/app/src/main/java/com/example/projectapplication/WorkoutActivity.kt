package com.example.projectapplication

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.widget.*
import kotlinx.android.synthetic.main.activity_training_done.*
import java.util.*

class WorkoutActivity : AppCompatActivity() {

    var exercisesCount = 5 // вот это вот надо на самом деле достать из баз данных
    var currentExercise = 1
    var repeatsCount = arrayOf(10, 12, 7, 25, 15)    // и это тоже
    var exNamesArray =
        arrayOf("Приседания", "Отжимания", "Жим лежа 100кг", "Подтягивания", "Еще какая нибудь хуйня") // и еще вот это
    lateinit var mChronometer: Chronometer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeExerciseLayout()
    }

    private fun makeExerciseLayout() {
        if (currentExercise <= exercisesCount) {
            setContentView(R.layout.activity_training_workout)
            var imagesIdsArray = setImagesArray()
            val nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            val currentExerciseNumber = findViewById<TextView>(R.id.ex_num)
            val exTitleText = findViewById<TextView>(R.id.ex_title)
            val exImage = findViewById<ImageView>(R.id.ex_image)
            currentExerciseNumber.text = "$currentExercise / $exercisesCount"
            exTitleText.text = exNamesArray[currentExercise - 1]
            exImage.setImageResource(imagesIdsArray[currentExercise - 1])
            nextLayoutButton.setOnClickListener {
                makePauseLayout()
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun makePauseLayout() {
        if (currentExercise < exercisesCount) {
            setContentView(R.layout.activity_training_pause)
            var imagesIdsArray = setImagesArray()
            var nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            val exNextImage = findViewById<ImageView>(R.id.ex_next_image)
            var exLayout = findViewById<LinearLayout>(R.id.ex_progress_list)
            exNextImage.setImageResource(imagesIdsArray[currentExercise])
            mChronometer = findViewById(R.id.ex_timer_text)
            mChronometer.base = SystemClock.elapsedRealtime()
            mChronometer.start()
            mChronometer.setOnChronometerTickListener {
                if (SystemClock.elapsedRealtime() - mChronometer.base > 30000) {
                    mChronometer.setTextColor(Color.parseColor("#FF0000"))
                }
            }
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
        quitDialog.setNegativeButton("Нет", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })
        quitDialog.create().show()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        makeQuitAlertDialog()

    }

    fun setImagesArray() : Array<Int>{
        return arrayOf(
            R.drawable.workout_image_1, R.drawable.workout_image_2, R.drawable.workout_image_3,
            R.drawable.workout_image_4, R.drawable.workout_image_5
        )
    }
}


