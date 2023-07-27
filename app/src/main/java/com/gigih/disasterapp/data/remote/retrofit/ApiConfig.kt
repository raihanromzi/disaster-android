package com.gigih.disasterapp.data.remote.retrofit

import com.gigih.disasterapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            // Only show log in debug mode
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            // Add logging interceptor to okhttp
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            // Create retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl("https://data.petabencana.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}