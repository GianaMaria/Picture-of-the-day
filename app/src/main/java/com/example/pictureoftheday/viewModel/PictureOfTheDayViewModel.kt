package com.example.pictureoftheday.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.BuildConfig
import com.example.pictureoftheday.model.entity.PODServerResponseData
import com.example.pictureoftheday.util.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<LiveDataOfTheDayData> = MutableLiveData(),
    private val api: Api = Api()
) :
    ViewModel() {

    fun getData(): LiveData<LiveDataOfTheDayData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = LiveDataOfTheDayData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            LiveDataOfTheDayData.Error(Throwable("You need API key"))
        } else {
            api.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(object :
                Callback<PODServerResponseData> {
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            LiveDataOfTheDayData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                LiveDataOfTheDayData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                LiveDataOfTheDayData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    liveDataForViewToObserve.value = LiveDataOfTheDayData.Error(t)
                }
            })
        }
    }
}
