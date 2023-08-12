package com.gigih.disasterapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GeometriesItem(

    @field:SerializedName("coordinates")
    val coordinates: List<Any?>? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("properties")
    val properties: Properties? = null
)
