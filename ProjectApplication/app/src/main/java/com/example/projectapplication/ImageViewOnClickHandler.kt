package com.example.projectapplication

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ImageViewOnClickHandler{
    companion object {
        fun bodyPictureClickHandle(drawable : Int, bodyType : String, mActivity : Activity, picture : ImageView, v: View){
            mActivity!!.findViewById<ImageView>(R.id.chosen_body_image)
                .setImageResource(drawable)
            mActivity!!.intent.putExtra("bodyType", bodyType)
            mActivity!!.intent.putExtra("isBodyChosen", true)
            AnimationAdapter.pictureClickAnimation(picture, v!!.context)
            mActivity!!.findViewById<TextView>(R.id.chosen_body_label).text = "Выбранный тип: $bodyType"
        }
    }

}