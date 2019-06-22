package com.raul.androidapps.softwaretesttandem.network

import com.raul.androidapps.softwaretesttandem.model.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("forecast")
    suspend fun getFiveDayForecast(
        @Query("id") id: Long,
        @Query("APPID")appid: String = "243748665f12fa5856b79211fcdb5cb6"): Response<ForecastResponse>

}
