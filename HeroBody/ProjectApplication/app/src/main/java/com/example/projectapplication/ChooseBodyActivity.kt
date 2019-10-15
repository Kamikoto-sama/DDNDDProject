package com.example.projectapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.projectapplication.AnimationAdapter.Companion.pictureClickAnimation
import kotlinx.android.synthetic.main.activity_choose_body.*

@Suppress("DEPRECATION")
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
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        intentToSend = Intent(this, MainActivity::class.java)
        if (intent.getBooleanExtra("needFirstLaunch", false) && !isHeightAndWeightPicked)
            startActivityForResult(Intent(this, FirstLaunchSliderActivity::class.java), 2)
        height = preferences.getString("height", "0")
        weight = preferences.getString("weight", "0")
        initializeBodySliderAdapter()
        setListenerForGoToMainButton()
        setListenerForOpenInfoButton()
        setListenersForSlideButtons()

    }

    private fun defineBodyType() {
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
        slider1_btn.setBackgroundResource(R.drawable.choose_body_btn_active)
        choose_body_view_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                when (p0) {
                    0 -> {
                        slider1_btn.setBackgroundResource(R.drawable.choose_body_btn_active)
                        slider2_btn.setBackgroundResource(R.drawable.background_for_body_btn)
                        slider3_btn.setBackgroundResource(R.drawable.background_for_body_btn)
                    }
                    1 -> {
                        slider1_btn.setBackgroundResource(R.drawable.background_for_body_btn)
                        slider2_btn.setBackgroundResource(R.drawable.choose_body_btn_active)
                        slider3_btn.setBackgroundResource(R.drawable.background_for_body_btn)
                    }
                    2 -> {
                        slider1_btn.setBackgroundResource(R.drawable.background_for_body_btn)
                        slider2_btn.setBackgroundResource(R.drawable.background_for_body_btn)
                        slider3_btn.setBackgroundResource(R.drawable.choose_body_btn_active)
                    }
                }
            }

        })
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
        var lBodyType = "ошибка"
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
        if (requestCode == 1) {
            val mDrawable: Int = data.getIntExtra("id", R.drawable.nick_png)
            val mBack: Int = data.getIntExtra("backId", R.drawable.background_for_fury_choice)
            intentToSend.putExtra("bodyPictureId", mDrawable)
            intentToSend.putExtra("bodyBackgroundId", getRectangledPictureOf(mBack))
            bodyType = data.getStringExtra("bodyType")
            isBodyChosen = data.getBooleanExtra("isBodyChosen", isBodyChosen)
            nick_fury_text.text = "начать"
            nick_fury_text.textSize = 44f
            chosen_body_image.setImageResource(mDrawable)
            chosen_body_image.setBackgroundResource(getRectangledPictureOf(mBack))
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            height = data.getIntExtra("height", 0).toString()
            weight = data.getIntExtra("weight", 0).toString()
            defineBodyType()
            isHeightAndWeightPicked = data.getBooleanExtra("isPicked", false)
            preferences
                .edit()
                .putString("weight", weight)
                .putString("height", height)
                .apply()

        }
    }

    private fun getRectangledPictureOf(id: Int): Int {
        val rectangledPictures: HashMap<Int, Int> = hashMapOf(
            R.drawable.bat_back to R.drawable.bat_back_rect,
            R.drawable.blacktiger_back to R.drawable.blacktiger_back_rect,
            R.drawable.cap_back to R.drawable.cap_back_rect,
            R.drawable.devil_back to R.drawable.devil_back_rect,
            R.drawable.garf_back to R.drawable.spiders_back_rect,
            R.drawable.goblin_back to R.drawable.goblin_back_rect,
            R.drawable.hohl_back to R.drawable.spiders_back_rect,
            R.drawable.starlord_back to R.drawable.starlord_back_rect,
            R.drawable.super_back to R.drawable.super_back_rect,
            R.drawable.thunder_back to R.drawable.thunder_back_rect,
            R.drawable.tobey_back to R.drawable.spiders_back_rect,
            R.drawable.torch_back to R.drawable.torch_back_rect
        )
        return rectangledPictures[id]!!
    }

    override fun onBackPressed() {
    }
}
