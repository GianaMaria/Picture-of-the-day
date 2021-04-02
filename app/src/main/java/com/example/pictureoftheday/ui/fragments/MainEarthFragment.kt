package com.example.pictureoftheday.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureoftheday.R
import com.example.pictureoftheday.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_main_earth.*
import kotlinx.android.synthetic.main.fragment_main_earth.indicator_earth
import kotlinx.android.synthetic.main.fragment_main_earth.tab_layout_earth

class MainEarthFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_earth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager_main_earth.adapter = ViewPagerAdapter(parentFragmentManager)
        indicator_earth.setViewPager(view_pager_main_earth)
        tab_layout_earth.setupWithViewPager(view_pager_main_earth)
    }

}