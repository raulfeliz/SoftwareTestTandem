package com.raul.androidapps.softwaretesttandem.model

data class Forecast(
    val dt: Long,
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>
)
