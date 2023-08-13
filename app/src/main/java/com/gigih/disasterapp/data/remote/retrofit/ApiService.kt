package com.gigih.disasterapp.data.remote.retrofit


import com.gigih.disasterapp.data.remote.response.ResponseAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reports/archive")
    fun getDisaster(
        @Query("start", encoded = true) startTime: String,
        @Query("end", encoded = true) endTime: String,
        @Query("admin") city: String? = null,
    ): Call<ResponseAPI>


}
