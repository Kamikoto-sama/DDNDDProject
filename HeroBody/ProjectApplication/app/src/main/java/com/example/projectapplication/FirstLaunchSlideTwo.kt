package com.example.projectapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


@Suppress("DEPRECATION")
class FirstLaunchSlideTwo : Fragment() {
    var currentPhrase = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_first_launch_slide_two, container, false)
        val doctorAngelaPhrases = arrayOf(
            getString(R.string.doctor_angel_phrase_1),
            getString(R.string.doctor_angel_phrase_2),
            getString(R.string.doctor_angel_phrase_3),
            getString(R.string.doctor_angel_phrase_4)
        )
        val doctorAngelImage = v.findViewById<ImageView>(R.id.doctor_angel)
        val doctorAngelText = v.findViewById<TextView>(R.id.doctor_angel_phrase)
        val doctorAngelBtn = v.findViewById<Button>(R.id.doctor_angel_phrase_btn)
        doctorAngelImage.setBackgroundDrawable(null)
        doctorAngelText.text = doctorAngelaPhrases[currentPhrase++]

        doctorAngelBtn.setOnClickListener {
            doctorAngelText.text = doctorAngelaPhrases[currentPhrase++]
            if (currentPhrase > 3) {
                currentPhrase = 3
                doctorAngelBtn.visibility = LinearLayout.GONE
            }
        }
        return v
    }


}
