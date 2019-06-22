package com.raul.androidapps.softwaretesttandem.model

data class Forecast(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>
)
