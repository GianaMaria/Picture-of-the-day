package com.example.pictureoftheday.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.pictureoftheday.BuildConfig
import com.example.pictureoftheday.R
import com.example.pictureoftheday.util.toast
import com.example.pictureoftheday.viewModel.EarthViewModel
import com.example.pictureoftheday.viewModel.LiveDataEarth
import kotlinx.android.synthetic.main.fragment_earth_photo.*


class EarthPhotoFragment : Fragment() {

    private val viewModel: EarthViewModel by lazy {
        ViewModelProviders.of(this).get(EarthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_earth_photo, container, false)
    }

    companion object {

        private const val KEY = "BUNDLE_KEY"

        fun newInstance(date: String) =
            EarthPhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY, date)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val date: String? = arguments?.getString(KEY)
        if (date != null) {
            viewModel.setDate(date)
        }
        viewModel.getData().observe(viewLifecycleOwner, Observer<LiveDataEarth> {
            if (date != null) {
                renderData(it, date)
            }
        })
    }

    private fun renderData(liveData: LiveDataEarth, date: String) {
        val arrayDate = date.split("-")
        val year = arrayDate[0]
        val month = arrayDate[1]
        val day = arrayDate[2]

        when (liveData) {
            is LiveDataEarth.Success -> {

                var uri = if (liveData.datePictureEarthGSON.isEmpty()) {
                    "https://api.nasa.gov/EPIC/archive/natural/2019/05/30/png/epic_1b_20190530011359.png?api_key=DEMO_KEY"
                } else {
                    "https://api.nasa.gov/EPIC/archive/natural/" +
                            "${year}/" +
                            "${month}/" +
                            day +
                            "/png/${liveData.datePictureEarthGSON[0].image}.png?api_key=${BuildConfig.NASA_API_KEY}"
                }
                Log.v("TAG", uri)

                image_view_earth_main.load(uri) {
                    lifecycle(this@EarthPhotoFragment)
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                }

            }
            is LiveDataEarth.Loading -> {
                //showLoading()
            }
            is LiveDataEarth.Error -> {
                toast(liveData.error.message)
            }
        }
    }
}

