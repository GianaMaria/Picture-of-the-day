package com.example.pictureoftheday.model.api

import com.example.pictureoftheday.model.entity.DatePictureEarthGSON
import com.example.pictureoftheday.model.entity.PODServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODServerResponseData>

    @GET("EPIC/api/natural/date/{date}")
    fun getPicturesData(@Path("date") date: String, @Query("api_key") apiKey: String) : Call<List<DatePictureEarthGSON>>

}