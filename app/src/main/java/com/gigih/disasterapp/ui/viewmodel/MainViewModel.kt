package com.gigih.disasterapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gigih.disasterapp.data.remote.response.GeometriesItem
import com.gigih.disasterapp.data.remote.response.ResponseAPI
import com.gigih.disasterapp.data.remote.retrofit.ApiConfig
import com.gigih.disasterapp.data.utils.LocalData
import retrofit2.Call

class MainViewModel : ViewModel() {

    private val _disasters = MutableLiveData<List<GeometriesItem>>()
    val disasters: LiveData<List<GeometriesItem>> = _disasters

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _searchQuery = MutableLiveData<String?>("ID-JK")
    val searchQuery: LiveData<String?> = _searchQuery

    private val _disasterType = MutableLiveData("flood")
    val disasterType: LiveData<String> = _disasterType


    companion object {
        private const val TAG = "MainViewModel"
        private const val START = "2017-12-04T00%3A00%3A00%2B0700"
        private const val END = "2017-12-06T05%3A00%3A00%2B0700"
    }

    fun refreshDisasterData() {
        // Call the API and update LiveData as necessary
        getDisasterData()
    }

    fun setSearchQuery(query: String) {
        val province = LocalData.getSupportedLocationType().filter {
            it.province == query
        }
        Log.d("query", province.toString())
        _searchQuery.value = province[0].code
        getDisasterData()
    }

    fun onButtonDisasterTypeClicked(type: String) {
        _disasterType.value = type
    }

    private fun getDisasterData() {
        val client = ApiConfig.getApiService().getDisaster(START, END)

        _isLoading.value = true
        client.enqueue(object : retrofit2.Callback<ResponseAPI> {
            override fun onResponse(
                call: Call<ResponseAPI>,
                response: retrofit2.Response<ResponseAPI>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseAPI = response.body()
                    if (responseAPI != null) {
                        val result = responseAPI.result.objects.output.geometries.filter {
                            it.properties.disasterType == _disasterType.value && it.properties.tags.instanceRegionCode == _searchQuery.value
                        }
                        _disasters.value = result
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