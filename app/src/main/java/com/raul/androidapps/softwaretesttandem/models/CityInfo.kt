package com.raul.androidapps.softwaretesttandem.models

import com.google.gson.annotations.SerializedName

data class CityInfo constructor(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
)
