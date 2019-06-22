package com.raul.androidapps.softwaretesttandem.model

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val message: Double,
    val list: List<Forecast>
)