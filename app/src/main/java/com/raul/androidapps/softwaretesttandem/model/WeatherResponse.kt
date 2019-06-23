package com.raul.androidapps.softwaretesttandem.model

import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.resources.ResourcesManager

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String,
    val wind: Wind
) {
    fun getMainDescription(): String? = weather.firstOrNull()?.main
    fun getWindSpeed(resourcesManager: ResourcesManager): String =
        String.format(resourcesManager.getString(R.string.wind_speed), wind.speed)

    fun getIconUrl(): String? = weather.firstOrNull()?.getIconUrl()
}