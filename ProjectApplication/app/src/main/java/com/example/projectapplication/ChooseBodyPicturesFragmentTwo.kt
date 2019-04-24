package com.example.projectapplication


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast


class ChooseBodyPicturesFragmentTwo : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_choose_body_pictures_fragment_two,null)
        var picture1 : ImageView = v.findViewById(R.id.choose_body_picture5)
        var picture2 : ImageView = v.findViewById(R.id.choose_body_picture6)
        var picture3 : ImageView = v.findViewById(R.id.choose_body_picture7)
        var picture4 : ImageView = v.findViewById(R.id.choose_body_picture8)
        picture1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture5)
                activity!!.intent.putExtra("bodyType","body_mid_1")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        picture2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture6)
                activity!!.intent.putExtra("bodyType","body_mid_2")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        picture3.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture7)
                activity!!.intent.putExtra("bodyType","body_mid_3")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        picture4.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture8)
                activity!!.intent.putExtra("bodyType","body_mid_4")
                activity!!.intent.putExtra("isBodyChosen", true)
            }
        })
        return v
    }
}
