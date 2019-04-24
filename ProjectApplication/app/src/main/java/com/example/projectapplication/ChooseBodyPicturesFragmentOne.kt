package com.example.projectapplication


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_choose_body.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.*


class ChooseBodyPicturesFragmentOne : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_choose_body_pictures_fragment_one,null)
        var picture1 : ImageView = v.findViewById(R.id.choose_body_picture1)
        var picture2 : ImageView = v.findViewById(R.id.choose_body_picture2)
        var picture3 : ImageView = v.findViewById(R.id.choose_body_picture3)
        var picture4 : ImageView = v.findViewById(R.id.choose_body_picture4)
        picture1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture1)
                activity!!.intent.putExtra("bodyType","body_skin_1")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        picture2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture2)
                activity!!.intent.putExtra("bodyType","body_skin_2")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        picture3.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture3)
                activity!!.intent.putExtra("bodyType","body_skin_3")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        picture4.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture4)
                activity!!.intent.putExtra("bodyType","body_skin_4")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        return v
    }
}
