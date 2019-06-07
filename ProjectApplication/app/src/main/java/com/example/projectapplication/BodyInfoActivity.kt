package com.example.projectapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_body_info.*

class BodyInfoActivity : AppCompatActivity() {

    lateinit var bodyType: String
    lateinit var mDataBase: DataBase
    lateinit var bodyCommonType: String
    lateinit var randomExercises: ArrayList<Exercise>
    var height = 0
    var id = 0
    var backId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBase = DataBase(this)
        setContentView(R.layout.activity_body_info)
        getDataFromIntent()
        getDataFromDB()
        setLayoutTextAndListeners()
        makeRandomExercsesLayout()
    }

    private fun getDataFromIntent() {
        bodyType = intent.getStringExtra("bodyType")
        id = intent.getIntExtra("id", 0)
        backId = intent.getIntExtra("backId", 0)
    }

    private fun setLayoutTextAndListeners() {
        body_type_name.text = bodyType
        body_type_type_and_height.text = "Рост : $height см. Тип: $bodyCommonType"
        confirm_btn.setOnClickListener {
            var mIntent = Intent()
            mIntent.putExtra("bodyType", bodyType)
            mIntent.putExtra("isBodyChosen", true)
            mIntent.putExtra("id", id)
            mIntent.putExtra("backId", backId)
            setResult(Activity.RESULT_OK, mIntent)
            finish()
        }
    }

    private fun getDataFromDB() {
        val bodyTypeInfo = mDataBase.getBodyTypeInfo(bodyType)
        height = bodyTypeInfo.growth
        bodyCommonType = bodyTypeInfo.type
        randomExercises = mDataBase.getRandomExercises(20)
    }

    private fun makeRandomExercsesLayout() {
        val mainLayout = findViewById<LinearLayout>(R.id.random_exercises_layout)
        for (i in 0 until randomExercises.size) {
            val oneExerciseLayout = LinearLayout(applicationContext)
            val exerciseImage = ImageView(applicationContext)
            val exerciseName = TextView(applicationContext)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.weight = 1f
            layoutParams.setMargins(0, 10, 0, 10)
            exerciseImage.layoutParams = layoutParams
            exerciseImage.setImageResource(matchIdWithPictures(randomExercises[i].image))
            exerciseName.text = randomExercises[i].name
            layoutParams.gravity = Gravity.CENTER and Gravity.RIGHT
            exerciseName.layoutParams = layoutParams
            oneExerciseLayout.addView(exerciseImage)
            oneExerciseLayout.addView(exerciseName)
            mainLayout.addView(oneExerciseLayout)

        }
    }

    private fun matchIdWithPictures(imageId: Int): Int {
        val idsDictionary: HashMap<Int, Int> = hashMapOf(
            13 to R.drawable.ex13,
            14 to R.drawable.ex14,
            15 to R.drawable.ex15,
            16 to R.drawable.ex16,
            17 to R.drawable.ex17,
            18 to R.drawable.ex18,
            19 to R.drawable.ex19,
            20 to R.drawable.ex20,
            21 to R.drawable.ex21,
            22 to R.drawable.ex22,
            23 to R.drawable.ex23,
            24 to R.drawable.ex24,
            25 to R.drawable.ex25,
            26 to R.drawable.ex26,
            27 to R.drawable.ex27,
            28 to R.drawable.ex28,
            29 to R.drawable.ex29,
            30 to R.drawable.ex30,
            31 to R.drawable.ex31,
            32 to R.drawable.ex32,
            33 to R.drawable.ex33,
            34 to R.drawable.ex34,
            35 to R.drawable.ex35,
            36 to R.drawable.ex36,
            37 to R.drawable.ex37,
            38 to R.drawable.ex38,
            39 to R.drawable.ex39,
            40 to R.drawable.ex40,
            41 to R.drawable.ex41,
            42 to R.drawable.ex42,
            43 to R.drawable.ex43,
            44 to R.drawable.ex44,
            45 to R.drawable.ex45,
            46 to R.drawable.ex46,
            47 to R.drawable.ex47,
            48 to R.drawable.ex48,
            49 to R.drawable.ex49,
            50 to R.drawable.ex50
        )
        return idsDictionary[imageId]!!
    }


}
