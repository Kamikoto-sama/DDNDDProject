package com.example.projectapplication

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_body_info.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mDataBase: DataBase
    lateinit var activity: Activity
    lateinit var preferences: SharedPreferences
    lateinit var bodyType: String
    val FIRST_LAUNCH = "firstLaunch"
    val CHOOSE_BODY_LAUNCH = "chooseBodyLaunch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBase = DataBase(this)
        bodyType = "undefined"
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        activity = this
        setContentView(R.layout.activity_main)
        main_act_body_height.text = "Рост: ${preferences.getInt("height", 0)}"
        main_act_body_type.text = "Тип: ${preferences.getString("type", "")}"
        main_act_body_image.setImageResource(preferences.getInt("bodyPictureId", 0))
        startFirstLaunchSliderActivity()
        setLayoutsListeners()
    }

    private fun setBodyLayoutText() {
        var height = mDataBase.getBodyTypeInfo(bodyType).growth
        var type = mDataBase.getBodyTypeInfo(bodyType).type
        preferences.edit().putInt("height", height).putString("type", type).apply()
        main_act_body_height.text = "Рост: $height"
        main_act_body_type.text = "Тип: $type"
    }

    private fun startFirstLaunchSliderActivity() {
        if (preferences.getBoolean(FIRST_LAUNCH, true)) {
            startActivityForResult(
                Intent(activity, ChooseBodyActivity::class.java).putExtra("needFirstLaunch", true),
                1
            )

        }
    }

    private fun makeChangeAlertDialog() {
        val changeBodyDialog = AlertDialog.Builder(this)
        changeBodyDialog.setTitle("Внимание!")
        changeBodyDialog.setMessage("Создастся новая программа тренировок, вы потеряете свой сохраненный прогресс. Продолжить?")
        changeBodyDialog.setPositiveButton("продолжить", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                startActivityForResult(
                    Intent(activity, ChooseBodyActivity::class.java)
                        .putExtra("isNeedToLaunch", true)
                        .putExtra("isFirstLaunch", false)
                        .putExtra("bodyType", intent.getStringExtra("bodyType"))
                    , 1
                )
            }

        })
        changeBodyDialog.setNegativeButton("отмена", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
            }

        })
        changeBodyDialog.create().show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) return
        bodyType = data.getStringExtra("bodyType")
        val mDrawable = data.getIntExtra("bodyPictureId", 0)
        setBodyLayoutText()
        main_act_body_image.setImageResource(mDrawable)
        preferences.edit().putBoolean(FIRST_LAUNCH, false).putString("bodyType", bodyType)
            .putInt("bodyPictureId", mDrawable).apply()
    }

    private fun setLayoutsListeners() {
        main_act_body_layout.setOnClickListener {
            startActivity(
                Intent(this, TrainingProgramActivity::class.java).putExtra(
                    "bodyType",
                    intent.getStringExtra("bodyType")
                )
            )
        }
        main_act_diet_layout.setOnClickListener {
            startActivity(Intent(this, DietActivity::class.java))
        }

        main_act_change_program_layout.setOnClickListener {
            makeChangeAlertDialog()
        }
    }
}
