package com.gigih.disasterapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class Tags(

    @field:SerializedName("instance_region_code")
    val instanceRegionCode: String? = null,

    @field:SerializedName("district_id")
    val districtId: Any? = null,

    @field:SerializedName("local_area_id")
    val localAreaId: String? = null
)
