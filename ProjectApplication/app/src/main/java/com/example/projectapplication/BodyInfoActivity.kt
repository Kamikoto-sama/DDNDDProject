package com.example.projectapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_body_info.*

class BodyInfoActivity : AppCompatActivity() {

    lateinit var bodyType: String
    lateinit var mDataBase: DataBase
    lateinit var bodyCommonType: String
    var height = 0
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBase = DataBase(this)
        setContentView(R.layout.activity_body_info)
        bodyType = intent.getStringExtra("bodyType")
        id = intent.getIntExtra("id", 0)
        height = mDataBase.getBodyTypeInfo(bodyType).growth
        bodyCommonType = mDataBase.getBodyTypeInfo(bodyType).type
        body_type_name.text = bodyType
        body_type_type_and_height.text = "Рост : $height см. Тип: $bodyCommonType"
        confirm_btn.setOnClickListener {
            var mIntent = Intent()
            mIntent.putExtra("bodyType", bodyType)
            mIntent.putExtra("isBodyChosen", true)
            mIntent.putExtra("id", id)
            setResult(Activity.RESULT_OK, mIntent)
            finish()
        }
    }
}
