package com.example.pictureoftheday.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureoftheday.R
import com.example.pictureoftheday.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_days_earth.*

class DaysEarthFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_days_earth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager_main_earth.adapter = ViewPagerAdapter(parentFragmentManager)
        indicator_earth.setViewPager(view_pager_main_earth)
        tab_layout_earth.setupWithViewPager(view_pager_main_earth)
    }

}