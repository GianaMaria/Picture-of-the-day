package com.example.pictureoftheday.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.BuildConfig
import com.example.pictureoftheday.model.entity.DatePictureEarthGSON
import com.example.pictureoftheday.util.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private const val TODAY = 0
private const val YESTERDAY = -1
private const val MY_DAY_BEFORE_YESTERDAY = -2

class EarthViewModel(
    private val liveDataForViewToObserve: MutableLiveData<LiveDataEarth> = MutableLiveData(),
    private val api: Api = Api()
) :
    ViewModel() {

    private var day: Int = 0
    private val date: String = getDate(day)

    fun getData(): LiveData<LiveDataEarth> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    fun setDay(numberDay: Int) {
        day = numberDay
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = LiveDataEarth.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            LiveDataEarth.Error(Throwable("You need API key"))
        } else {
            api.getRetrofitImpl().getPicturesData(date, apiKey).enqueue(object :
                Callback<List<DatePictureEarthGSON>> {
                override fun onResponse(
                    call: Call<List<DatePictureEarthGSON>>,
                    response: Response<List<DatePictureEarthGSON>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            LiveDataEarth.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                LiveDataEarth.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                LiveDataEarth.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<List<DatePictureEarthGSON>>, t: Throwable) {
                    liveDataForViewToObserve.value = LiveDataEarth.Error(t)
                }
            })
        }
    }

    private fun getDate(day: Int): String {
        val date = GregorianCalendar()
        date.roll(Calendar.DAY_OF_MONTH, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date.time)
    }
}