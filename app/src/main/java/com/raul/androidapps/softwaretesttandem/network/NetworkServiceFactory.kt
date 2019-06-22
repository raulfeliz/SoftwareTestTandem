package com.raul.androidapps.softwaretesttandem.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class NetworkServiceFactory @Inject constructor() {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    }

    @Volatile
    private var instance: OpenWeatherApi? = null

    fun getServiceInstance(): OpenWeatherApi =
        instance ?: synchronized(this) {
            instance ?: buildNetworkService().also { instance = it }
        }

    private fun buildNetworkService(): OpenWeatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(OpenWeatherApi::class.java)


}