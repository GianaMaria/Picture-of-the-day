package com.example.pictureoftheday.ui.adapter

import android.icu.text.SimpleDateFormat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.pictureoftheday.ui.fragments.EarthPhotoFragment
import com.example.pictureoftheday.util.Constants
import java.util.*

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        EarthPhotoFragment.newInstance(getDate(Constants.TODAY)),
        EarthPhotoFragment.newInstance(getDate(Constants.YESTERDAY)),
        EarthPhotoFragment.newInstance(getDate(Constants.MY_DAY_BEFORE_YESTERDAY))
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[Constants.TODAY_FRAGMENT]
            1 -> fragments[Constants.YESTERDAY_FRAGMENT]
            2 -> fragments[Constants.MY_DAY_BEFORE_YESTERDAY_FRAGMENT]
            else -> fragments[Constants.TODAY_FRAGMENT]
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> Constants.TODAY_STRING
            1 -> Constants.YESTERDAY_STRING
            2 -> Constants.MY_DAY_BEFORE_YESTERDAY_STRING
            else -> Constants.TODAY_STRING
        }
    }

    private fun getDate(day: Int): String {
        val date = GregorianCalendar()
        date.roll(Calendar.DAY_OF_MONTH, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date.time)
    }

}