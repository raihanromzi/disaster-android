package com.gigih.disasterapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseAPI(

    @field:SerializedName("result")
    val result: Result,

    @field:SerializedName("statusCode")
    val statusCode: Int
)