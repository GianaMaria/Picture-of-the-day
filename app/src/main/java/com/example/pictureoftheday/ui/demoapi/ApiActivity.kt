package com.example.pictureoftheday.ui.demoapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pictureoftheday.R
import kotlinx.android.synthetic.main.activity_api.*

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        indicator.setViewPager(view_pager)
        tab_layout.setupWithViewPager(view_pager)
    }
}