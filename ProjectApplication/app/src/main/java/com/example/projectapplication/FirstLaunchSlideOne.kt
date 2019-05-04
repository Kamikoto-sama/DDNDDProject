package com.example.projectapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


@Suppress("DEPRECATION")
class FirstLaunchSlideOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v : View = inflater.inflate(R.layout.fragment_first_launch_slide_one, container, false)
        val nickFuryImage = v.findViewById<ImageView>(R.id.nick_fury)
        nickFuryImage.setBackgroundDrawable(null)
        v.background = null
        return v
    }


}
