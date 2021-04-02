package com.example.pictureoftheday.util

import android.icu.text.SimpleDateFormat
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

fun getDate(day: Int): String {
    val date = GregorianCalendar()
    date.roll(Calendar.DAY_OF_MONTH, day)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(date.time)
}

fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.BOTTOM, 0, 250)
        show()
    }
}
