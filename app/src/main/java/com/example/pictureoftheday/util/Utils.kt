package com.example.pictureoftheday.util

import android.icu.text.SimpleDateFormat
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
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

fun enlargingThePicture(imageView: EquilateralImageView, container: ViewGroup?) {

    var isExpanded = false

    imageView.setOnClickListener {
        isExpanded = !isExpanded

        TransitionManager.beginDelayedTransition(
            container, TransitionSet()
                .addTransition(ChangeBounds())
                .addTransition(ChangeImageTransform())
        )
        val params: ViewGroup.LayoutParams = imageView.layoutParams
        params.height =
            if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT
            else ViewGroup.LayoutParams.WRAP_CONTENT

        imageView.layoutParams = params
        imageView.scaleType =
            if (isExpanded) ImageView.ScaleType.CENTER_CROP
            else ImageView.ScaleType.FIT_CENTER
    }
}

