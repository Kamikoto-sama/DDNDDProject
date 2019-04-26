package com.example.projectapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import kotlinx.android.synthetic.main.fragment_first_launch_slide_three.*


class FirstLaunchSlideThree : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_first_launch_slide_three, container, false)
        var heightPicker : NumberPicker = v.findViewById(R.id.enter_height)
        var weightPicker : NumberPicker = v.findViewById(R.id.enter_weight)
        heightPicker.minValue = 150
        heightPicker.maxValue = 210
        weightPicker.minValue = 50
        weightPicker.maxValue = 120
        heightPicker.value = 170
        weightPicker.value = 70
        return v
    }


}
