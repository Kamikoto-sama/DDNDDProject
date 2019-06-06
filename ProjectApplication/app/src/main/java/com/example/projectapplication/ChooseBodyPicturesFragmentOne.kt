package com.example.projectapplication

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projectapplication.ImageViewOnClickHandler.Companion.bodyPictureClickHandle

class ChooseBodyPicturesFragmentOne : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_choose_body_pictures_fragment_one, null)
        val picture1: ImageView = v.findViewById(R.id.choose_body_picture1)
        val picture2: ImageView = v.findViewById(R.id.choose_body_picture2)
        val picture3: ImageView = v.findViewById(R.id.choose_body_picture3)
        val picture4: ImageView = v.findViewById(R.id.choose_body_picture4)
        picture1.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture1,"Пламя", activity!!, picture1, v)
        }
        picture2.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture2,"Гоблин", activity!!, picture2, v)
        }
        picture3.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture3,"Паук-1", activity!!, picture3, v)
        }
        picture4.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture4,"Паук-2", activity!!, picture4, v)
        }

        return v
    }
}