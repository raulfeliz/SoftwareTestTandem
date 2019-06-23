package com.raul.androidapps.softwaretesttandem.model

import java.util.*

data class Forecast(
    val dt: Long,
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>
) {
    fun getDate(): Date = Date(dt * 1000)
}
