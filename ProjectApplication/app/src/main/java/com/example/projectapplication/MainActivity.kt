package com.example.projectapplication

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var activity: Activity
    lateinit var preferences: SharedPreferences
    val FIRST_LAUNCH = "firstLaunch"
    val CHOOSE_BODY_LAUNCH = "chooseBodyLaunch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        activity = this
        setContentView(R.layout.activity_main)
        startFirstLaunchSliderActivity()


        main_act_body_btn.setOnClickListener {
            startActivity(
                Intent(this, TrainingProgramActivity::class.java).putExtra(
                    "bodyType",
                    intent.getStringExtra("bodyType")
                )
            )
        }
        main_act_diet_btn.setOnClickListener {
            startActivity(Intent(this, DietActivity::class.java))
        }

        main_act_change_program_btn.setOnClickListener {
            makeChangeAlertDialog()
        }
    }

    private fun startFirstLaunchSliderActivity() {
        if (preferences.getBoolean(FIRST_LAUNCH, true)) {
            startActivity(Intent(activity, FirstLaunchSliderActivity::class.java))
            preferences.edit().putBoolean(FIRST_LAUNCH, false).apply()
            finish()
        }
    }

    private fun makeChangeAlertDialog() {
        val changeBodyDialog = AlertDialog.Builder(this)
        changeBodyDialog.setTitle("Внимание!")
        changeBodyDialog.setMessage("Создастся новая программа тренировок, вы потеряете свой сохраненный прогресс. Продолжить?")
        changeBodyDialog.setPositiveButton("продолжить", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                startActivity(
                    Intent(activity, ChooseBodyActivity::class.java)
                        .putExtra("isNeedToLaunch", true)
                        .putExtra("isFirstLaunch", false)
                        .putExtra("bodyType", intent.getStringExtra("bodyType"))
                )
                finish()
            }

        })
        changeBodyDialog.setNegativeButton("отмена", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

        })
        changeBodyDialog.create().show()
    }
}
