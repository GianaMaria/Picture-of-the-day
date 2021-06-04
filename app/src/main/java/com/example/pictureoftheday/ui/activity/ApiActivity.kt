package com.example.pictureoftheday.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pictureoftheday.R
import com.example.pictureoftheday.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_api.*

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        indicator_earth.setViewPager(view_pager)
        tab_layout_earth.setupWithViewPager(view_pager)
    }
}