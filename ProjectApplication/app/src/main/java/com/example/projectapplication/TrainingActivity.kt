package com.example.projectapplication

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_training.*

class TrainingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        workout_activity_text.text = intent.getStringExtra("text")
        val mLayout: LinearLayout = findViewById(R.id.m_linear_layout)
        val mScrollView : ScrollView = findViewById(R.id.m_scroll_view)
        var count = 0
        begin_workout_btn.setOnClickListener {
            count++
            val newButton = Button(applicationContext)
            val mLayoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            mLayoutParams.setMargins(10,10,10,10)
            newButton.text = if (count % 2 == 0) "Больше кнопок" else "богу кнопок"
            newButton.layoutParams = mLayoutParams
            newButton.id = count
            newButton.textSize = 24f
            newButton.fontFeatureSettings = "Franklin"
            newButton.setTypeface(null, Typeface.BOLD_ITALIC)
            newButton.setBackgroundResource(R.drawable.begin_workout_button_shape)
            mScrollView.scrollY += 100
            mLayout.addView(newButton)
            newButton.setOnClickListener { v ->
                m_linear_layout.removeView(v)
                mScrollView.scrollY -= 100
            }
        }
    }
}
