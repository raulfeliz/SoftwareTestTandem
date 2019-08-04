package com.raul.androidapps.softwaretesttandem.model



class TotalForecastResponse  constructor(
    val currentWeather: WeatherResponse?,
    val nextFiveDaysWeather: List<DayForecast>
)