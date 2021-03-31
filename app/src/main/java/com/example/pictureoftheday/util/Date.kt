package com.example.pictureoftheday.util

import android.icu.text.SimpleDateFormat
import java.util.*

fun getDate(day: Int): String {
    val date = GregorianCalendar()
    date.roll(Calendar.DAY_OF_MONTH, day)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(date.time)
}
