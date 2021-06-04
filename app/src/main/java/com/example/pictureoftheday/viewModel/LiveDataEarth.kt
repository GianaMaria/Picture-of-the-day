package com.example.pictureoftheday.viewModel

import com.example.pictureoftheday.model.entity.DatePictureEarthGSON

sealed class LiveDataEarth {
    data class Success(val datePictureEarthGSON: List<DatePictureEarthGSON>) : LiveDataEarth()
    data class Error(val error: Throwable) : LiveDataEarth()
    data class Loading(val progress: Int?) : LiveDataEarth()
}