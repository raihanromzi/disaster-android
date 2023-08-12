package com.gigih.disasterapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gigih.disasterapp.data.remote.response.GeometriesItem
import com.gigih.disasterapp.data.remote.response.ResponseAPI
import com.gigih.disasterapp.data.remote.retrofit.ApiConfig
import retrofit2.Call

class MainViewModel : ViewModel() {

    init {
        getDisasterData()
    }

    private val _disasters = MutableLiveData<List<GeometriesItem>>()
    val disasters: LiveData<List<GeometriesItem>> = _disasters

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainActivity"
        private const val START = "2017-12-04T00%3A00%3A00%2B0700"
        private const val END = "2017-12-06T05%3A00%3A00%2B0700"
    }

    private fun getDisasterData() {
        val client = ApiConfig.getApiService().getDisaster(START, END)

        client.enqueue(object : retrofit2.Callback<ResponseAPI> {
            override fun onResponse(
                call: Call<ResponseAPI>,
                response: retrofit2.Response<ResponseAPI>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseAPI = response.body()
                    if (responseAPI != null) {
                        Log.d(TAG, "onResponse: $responseAPI")
                        _disasters.value = responseAPI.result.objects.output.geometries
                    }
                } else {
                    _isLoading.value = true
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}