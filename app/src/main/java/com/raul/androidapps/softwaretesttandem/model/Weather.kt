package com.raul.androidapps.softwaretesttandem.model

import com.raul.androidapps.softwaretesttandem.network.NetworkServiceFactory

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
){
    fun getIconUrl(): String = String.format(NetworkServiceFactory.TEMPLATE_ICON_URL, icon)
}
