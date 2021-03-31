package com.example.pictureoftheday.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pictureoftheday.R
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        checkThemeOne.setOnClickListener {
//            if (checkThemeOne.isChecked){
//                activity?.setTheme(R.style.AppTheme)
//                activity?.let { it1 -> recreate(it1) }
//            } else {
//                activity?.setTheme(R.style.AppThemeSecond)
//                activity?.let { it1 -> recreate(it1) }
//            }
//        }
    }
}
