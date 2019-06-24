package com.raul.androidapps.softwaretesttandem.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    var country: String? = null
) {
    fun getSearchName(): String {
        var searchName = name
        country?.let { country ->
            searchName += ", $country"
        }
        return searchName
    }
}

