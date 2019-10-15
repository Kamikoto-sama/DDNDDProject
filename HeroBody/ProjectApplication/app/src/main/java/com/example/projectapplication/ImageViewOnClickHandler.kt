package com.example.projectapplication

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ImageViewOnClickHandler {
    companion object {
        fun bodyPictureClickHandle(
            drawable: Int,
            backDrawable: Int,
            bodyType: String,
            mActivity: Activity,
            picture: ImageView,
            v: View
        ) {
            AnimationAdapter.pictureClickAnimation(picture, v.context)
            mActivity.startActivityForResult(
                Intent(mActivity, BodyInfoActivity::class.java).putExtra(
                    "bodyType",
                    bodyType
                ).putExtra("id", drawable).putExtra("backId", backDrawable), 1
            )
        }
    }

}