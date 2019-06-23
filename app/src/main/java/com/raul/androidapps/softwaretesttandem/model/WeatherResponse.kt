package com.raul.androidapps.softwaretesttandem.model

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String,
    val wind: Wind
) {
    fun getMainDescription(): String? = weather.firstOrNull()?.main
    fun getIconUrl(): String? = weather.firstOrNull()?.getIconUrl()
}