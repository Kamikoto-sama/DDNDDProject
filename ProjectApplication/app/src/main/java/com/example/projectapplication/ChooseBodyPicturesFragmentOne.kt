package com.example.projectapplication


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_choose_body.*
import kotlinx.android.synthetic.main.fragment_choose_body_pictures_fragment_one.*


class ChooseBodyPicturesFragmentOne : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
when(v?.id){

}
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var viewPagerView : View = inflater!!.inflate(R.layout.fragment_choose_body_pictures_fragment_one,container,false)
        return viewPagerView
    }
}
