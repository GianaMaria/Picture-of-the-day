package com.example.pictureoftheday.util

import com.example.pictureoftheday.ui.picture.PictureOfTheDayAPI
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val API_KEY = "api_key"

private const val BASE_URL = "https://api.nasa.gov/"
const val URL_IMAGES_EARTH = "https://api.nasa.gov/EPIC/api/natural/date/"
const val URL_IMAGES_MARS = "https://api.nasa.gov/mars-photos/api/"

class Api {

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .setLenient()
        .create()

    fun getRetrofitImpl(): PictureOfTheDayAPI =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createOkHttpClient(PODInterceptor()))
            .build()
            .create(PictureOfTheDayAPI::class.java)

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()


    inner class PODInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}