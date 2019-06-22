package com.raul.androidapps.softwaretesttandem.model

import com.raul.androidapps.softwaretesttandem.network.NetworkServiceFactory

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String,
    val wind: Wind
){
    fun getMainDescription(): String? = weather.firstOrNull()?.main
    fun getIconUrl(): String = String.format(NetworkServiceFactory.TEMPLATE_ICON_URL, weather.firstOrNull()?.icon)
}