package com.example.projectapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import com.kevalpatel2106.rulerpicker.RulerValuePicker
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener
import kotlinx.android.synthetic.main.fragment_first_launch_slide_three.*


class FirstLaunchSlideThree : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_first_launch_slide_three, container, false)
        val heightPicker : RulerValuePicker = v.findViewById(R.id.enter_height)
        val weightPicker : RulerValuePicker = v.findViewById(R.id.enter_weight)
        val heightPickedText : TextView = v.findViewById(R.id.picked_height_text)
        val weightPickedText : TextView = v.findViewById(R.id.picked_weight_text)
        heightPicker.selectValue(170)
        weightPicker.selectValue(70)
        heightPicker.setValuePickerListener(object : RulerValuePickerListener{
            override fun onValueChange(selectedValue: Int) {

            }

            override fun onIntermediateValueChange(selectedValue: Int) {
                heightPickedText.text = selectedValue.toString()
            }

        })
        weightPicker.setValuePickerListener(object : RulerValuePickerListener{
            override fun onValueChange(selectedValue: Int) {

            }

            override fun onIntermediateValueChange(selectedValue: Int) {
                weightPickedText.text = selectedValue.toString()
            }

        })
        return v
    }


}
