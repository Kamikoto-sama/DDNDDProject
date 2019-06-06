package com.example.projectapplication

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_training_done.*

class WorkoutActivity : AppCompatActivity() {

    lateinit var mDataBase: DataBase
    lateinit var exercisesArray : ArrayList<Exercise>
    var currentDay = 0
    var currentExercise = 1
lateinit var mChronometer : Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        mDataBase = DataBase(this)
        currentDay = intent.getIntExtra("day", 0)
        exercisesArray = mDataBase.getTraining(currentDay)
        super.onCreate(savedInstanceState)
        makeExerciseLayout()
        var myString = getString(R.string.main_text)
    }

    private fun makeExerciseLayout() {
        if (currentExercise <= exercisesArray.count()) {
            setContentView(R.layout.activity_training_workout)
            var imagesIdsArray = setImagesArray()
            val nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            val currentExerciseNumber = findViewById<TextView>(R.id.ex_num)
            val exTitleText = findViewById<TextView>(R.id.ex_title)
            val exImage = findViewById<ImageView>(R.id.ex_image)
            currentExerciseNumber.text = "$currentExercise / ${exercisesArray.count()}"
            exTitleText.text = exercisesArray[currentExercise - 1].name
            exImage.setImageResource(exercisesArray[currentExercise - 1].image)
            nextLayoutButton.setOnClickListener {
                makePauseLayout()
            }
        }

    }

    private fun makePauseLayout() {
        if (currentExercise < exercisesArray.count()) {
            setContentView(R.layout.activity_training_pause)
            var imagesIdsArray = setImagesArray()
            var nextLayoutButton = findViewById<FloatingActionButton>(R.id.btn_next_layout)
            val exNextImage = findViewById<ImageView>(R.id.ex_next_image)
            var exLayout = findViewById<LinearLayout>(R.id.ex_progress_list)
            exNextImage.setImageResource(imagesIdsArray[currentExercise])
            mChronometer = setChronometer()
            nextLayoutButton.setOnClickListener {
                currentExercise++
                makeExerciseLayout()
            }
            for (i in 0 until exercisesArray.count())
                exLayout.addView((makeOneExerciseLine(i)))
        } else {
            makeDoneLayout()
        }


    }

    private fun setChronometer(): Chronometer{
        val mChronometer : Chronometer = findViewById(R.id.ex_timer_text)
        mChronometer.base = SystemClock.elapsedRealtime()
        mChronometer.start()
        mChronometer.setOnChronometerTickListener {
            if (SystemClock.elapsedRealtime() - mChronometer.base > 30000) {
                mChronometer.setTextColor(Color.parseColor("#FF5730"))
            }
        }
        return mChronometer
    }

    private fun makeOneExerciseLine(i: Int): LinearLayout {
        val oneExLayout = LinearLayout(applicationContext)
        val exListItem = TextView(applicationContext)
        val exListImage = ImageView(applicationContext)
        oneExLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        oneExLayout.orientation = LinearLayout.HORIZONTAL
        exListItem.text = "       ${exercisesArray[i].currentCount} ${exercisesArray[i].name}"
        exListItem.textSize = 16f
        exListItem.typeface = Typeface.create("Franklin", Typeface.ITALIC)
        exListItem.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            50
        )
        exListImage.layoutParams = LinearLayout.LayoutParams(40, 40)

        if (i < currentExercise || i == 0) {
            exListImage.setImageResource(R.drawable.another_comlete_arrow)
        } else if (i == currentExercise) {
            exListImage.setImageResource(R.drawable.next_exercise_arrow)
            exListItem.typeface = Typeface.create("Franklin", Typeface.BOLD_ITALIC)
        } else exListImage.visibility = ImageView.INVISIBLE

        oneExLayout.addView(exListImage)
        oneExLayout.addView(exListItem)
        return oneExLayout
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

    private fun setImagesArray(): Array<Int> {
        return arrayOf(
            R.drawable.workout_image_1, R.drawable.workout_image_2, R.drawable.workout_image_3,
            R.drawable.workout_image_4, R.drawable.workout_image_5
        )
    }
}


