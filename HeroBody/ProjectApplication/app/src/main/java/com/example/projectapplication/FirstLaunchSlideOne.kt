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
import kotlinx.android.synthetic.main.fragment_first_launch_slide_one.*


@Suppress("DEPRECATION")
class FirstLaunchSlideOne : Fragment() {


    var currentPhrase = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_first_launch_slide_one, container, false)
        val nickFuryPhrasesArray: Array<String> = arrayOf(
            getString(R.string.nick_fury_phrase_1),
            getString(R.string.nick_fury_phrase_2),
            getString(R.string.nick_fury_phrase_3),
            getString(R.string.nick_fury_phrase_4)
        )
        val nickFuryImage = v.findViewById<ImageView>(R.id.nick_fury)
        val nickFuryText = v.findViewById<TextView>(R.id.nick_fury_phrase)
        val nickFuryBtn = v.findViewById<Button>(R.id.nick_fury_phrase_btn)
        nickFuryImage.setBackgroundDrawable(null)
        nickFuryText.text = nickFuryPhrasesArray[currentPhrase++]
        nickFuryBtn.setOnClickListener {
            nickFuryText.text = nickFuryPhrasesArray[currentPhrase++]
            if (currentPhrase > 3) {
                currentPhrase = 3
                nickFuryBtn.visibility = LinearLayout.GONE
            }
        }
        v.background = null
        return v
    }


}
