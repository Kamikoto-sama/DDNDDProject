package com.example.projectapplication


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class ChooseBodyPicturesFragmentThree : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_choose_body_pictures_fragment_three,null)
        var picture1 : ImageView = v.findViewById(R.id.choose_body_picture9)
        var picture2 : ImageView = v.findViewById(R.id.choose_body_picture10)
        var picture3 : ImageView = v.findViewById(R.id.choose_body_picture11)
        var picture4 : ImageView = v.findViewById(R.id.choose_body_picture12)
        picture1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture9)
                activity!!.intent.putExtra("bodyType","body9")
            }
        })
        picture2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture10)
                activity!!.intent.putExtra("bodyType","body10")
            }
        })
        picture3.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture11)
                activity!!.intent.putExtra("bodyType","body11")
            }
        })
        picture4.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                activity!!.findViewById<ImageView>(R.id.chosen_body_image).setImageResource(R.drawable.choose_body_picture12)
                activity!!.intent.putExtra("bodyType","body12")
            }
        })
        return v
    }

}
