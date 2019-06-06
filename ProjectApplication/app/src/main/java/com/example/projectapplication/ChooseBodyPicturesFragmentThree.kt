package com.example.projectapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projectapplication.ImageViewOnClickHandler.Companion.bodyPictureClickHandle

class ChooseBodyPicturesFragmentThree : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_choose_body_pictures_fragment_three, null)
        val picture1: ImageView = v.findViewById(R.id.choose_body_picture9)
        val picture2: ImageView = v.findViewById(R.id.choose_body_picture10)
        val picture3: ImageView = v.findViewById(R.id.choose_body_picture11)
        val picture4: ImageView = v.findViewById(R.id.choose_body_picture12)
        picture1.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture9,"Капитан", activity!!, picture1, v)
        }
        picture2.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture10,"Суперчеловек", activity!!, picture2, v)
        }
        picture3.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture11,"Лорд", activity!!, picture3, v)
        }
        picture4.setOnClickListener { v ->
            bodyPictureClickHandle(R.drawable.choose_body_picture12,"Богач", activity!!, picture4, v)
        }
        return v
    }
}
