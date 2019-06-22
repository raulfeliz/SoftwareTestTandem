package com.raul.androidapps.softwaretesttandem.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    var country: String? = null
)

