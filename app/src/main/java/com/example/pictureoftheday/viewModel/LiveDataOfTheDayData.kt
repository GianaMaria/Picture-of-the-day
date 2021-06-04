package com.example.pictureoftheday.viewModel

import com.example.pictureoftheday.model.entity.PODServerResponseData

sealed class LiveDataOfTheDayData {
    data class Success(val serverResponseData: PODServerResponseData) : LiveDataOfTheDayData()
    data class Error(val error: Throwable) : LiveDataOfTheDayData()
    data class Loading(val progress: Int?) : LiveDataOfTheDayData()
}