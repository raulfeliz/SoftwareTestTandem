package com.raul.androidapps.softwaretesttandem.network

import com.raul.androidapps.softwaretesttandem.BuildConfig
import com.raul.androidapps.softwaretesttandem.model.ForecastResponse
import com.raul.androidapps.softwaretesttandem.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("forecast")
    suspend fun getFiveDayForecast(
        @Query("id") id: Long,
        @Query("APPID")appid: String = BuildConfig.OPEN_WEATHER_API_KEY): Response<ForecastResponse>

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("id") id: Long,
        @Query("APPID")appid: String = BuildConfig.OPEN_WEATHER_API_KEY): Response<WeatherResponse>

}
