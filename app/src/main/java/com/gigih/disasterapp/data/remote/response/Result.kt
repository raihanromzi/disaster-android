package com.gigih.disasterapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class Result(

    @field:SerializedName("objects")
    val objects: Objects? = null,

    @field:SerializedName("bbox")
    val bbox: List<Any?>? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("arcs")
    val arcs: List<Any?>? = null
)
