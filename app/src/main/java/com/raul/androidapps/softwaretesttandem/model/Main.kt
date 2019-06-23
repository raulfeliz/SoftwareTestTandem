package com.raul.androidapps.softwaretesttandem.model

import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.resources.ResourcesManager

data class Main(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float,
    val humidity: Int
){
    fun getTemperature(): String = "${temp}º"
    fun getMinTemp(resourcesManager: ResourcesManager): String =
        String.format(resourcesManager.getString(R.string.temp_min), temp_min)
    fun getMaxTemp(resourcesManager: ResourcesManager): String =
        String.format(resourcesManager.getString(R.string.temp_max), temp_max)
    fun getHumidity(resourcesManager: ResourcesManager): String =
        String.format(resourcesManager.getString(R.string.humidity), humidity)
}
