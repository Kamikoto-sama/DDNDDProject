package com.example.projectapplication

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.ImageView

class AnimationAdapter{
    companion object {
        fun pictureClickAnimation(picture: ImageView, context: Context) {
            var mAnimation = AnimationUtils.loadAnimation(context, R.anim.body_picture_choose)
            picture.startAnimation(mAnimation)
        }
    }
}