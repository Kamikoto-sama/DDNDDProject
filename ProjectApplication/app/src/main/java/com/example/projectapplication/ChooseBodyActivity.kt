package com.example.projectapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projectapplication.AnimationAdapter.Companion.pictureClickAnimation
import kotlinx.android.synthetic.main.activity_choose_body.*

class ChooseBodyActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    lateinit var intentToSend: Intent
    var isBodyChosen = false
    lateinit var bodyType: String
    lateinit var weight: String
    lateinit var height: String
    var isHeightAndWeightPicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_body)
        intentToSend = Intent(this, MainActivity::class.java)
        if(intent.getBooleanExtra("needFirstLaunch", false) && !isHeightAndWeightPicked)
            startActivityForResult(Intent(this,FirstLaunchSliderActivity::class.java),2)
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        if (intent.getStringExtra("bodyType") != null)
            bodyType = intent.getStringExtra("bodyType")
        initializeBodySliderAdapter()
        setListenerForGoToMainButton()
        setListenerForOpenInfoButton()
        setListenersForSlideButtons()
        defineBodyType()

    }

    private fun defineBodyType() {

            weight = preferences.getString("weight", "0").toString()
            height = preferences.getString("height", "0").toString()
            countBodyTypeByFormula(height.toDouble(), weight.toInt())
    }

    private fun setListenerForOpenInfoButton() {
        chosen_body_image.setOnClickListener {
            if (intent.getStringExtra("bodyType") != null) {
                startActivityForResult(Intent(this, BodyInfoActivity::class.java), 1)
            }
        }
    }

    private fun setListenerForGoToMainButton() {
        nick_fury_text.setOnClickListener {
            if (isBodyChosen) {
                intentToSend.putExtra("bodyType", bodyType)
                setResult(Activity.RESULT_OK, intentToSend)
                finish()
            }
        }
    }

    private fun initializeBodySliderAdapter(): ChooseBodySliderAdapter {
        var adapter = ChooseBodySliderAdapter(supportFragmentManager)
        adapter.addFragment(ChooseBodyPicturesFragmentOne())
        adapter.addFragment(ChooseBodyPicturesFragmentTwo())
        adapter.addFragment(ChooseBodyPicturesFragmentThree())
        choose_body_view_pager.adapter = adapter
        return adapter
    }

    private fun setListenersForSlideButtons() {
        slider1_btn.setOnClickListener {
            pictureClickAnimation(slider1_btn, this)
            choose_body_view_pager.currentItem = 0
        }
        slider2_btn.setOnClickListener {
            pictureClickAnimation(slider2_btn, this)
            choose_body_view_pager.currentItem = 1
        }
        slider3_btn.setOnClickListener {
            pictureClickAnimation(slider3_btn, this)
            choose_body_view_pager.currentItem = 2
        }
    }

    private fun countBodyTypeByFormula(height: Double, weight: Int) {
        val metersHeight: Double = height / 100
        val formulaResult: Double = (weight / metersHeight / metersHeight)
        var lBodyType = ""
        when {
            formulaResult < 18.5 -> {
                lBodyType = "Эктоморф"
                choose_body_view_pager.currentItem = 0
            }
            formulaResult in 18.5..24.9 -> {
                lBodyType = "Мезоморф"
                choose_body_view_pager.currentItem = 1
            }
            formulaResult > 25 -> {
                lBodyType = "Эндоморф"
                choose_body_view_pager.currentItem = 2
            }
        }
            nick_fury_text.text = "Подходящий тип : $lBodyType"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) return
        if(requestCode==1){
            var mDrawable : Int = data.getIntExtra("id", 0)
            bodyType = data.getStringExtra("bodyType")
            isBodyChosen = data.getBooleanExtra("isBodyChosen", isBodyChosen)
            nick_fury_text.text = "начать"
            nick_fury_text.textSize = 50f
            chosen_body_image.setImageResource(mDrawable)
        }
        if(requestCode==2 && resultCode== Activity.RESULT_OK){
            height = data.getIntExtra("height", 0).toString()
            weight = data.getIntExtra("weight", 0).toString()
            isHeightAndWeightPicked = data.getBooleanExtra("isPicked", false)
            preferences
                .edit()
                .putString("weight", weight)
                .putString("height", height)
                .apply()

        }
    }
}
