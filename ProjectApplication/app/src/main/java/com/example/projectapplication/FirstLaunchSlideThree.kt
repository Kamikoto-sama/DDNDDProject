package com.example.projectapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
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
        heightPicker.selectValue(170)
        weightPicker.selectValue(70)
        return v
    }


}
