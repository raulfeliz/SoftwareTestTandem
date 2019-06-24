package com.raul.androidapps.softwaretesttandem.network

import com.raul.androidapps.softwaretesttandem.network.NetworkServiceFactory.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class NetworkServiceFactoryImp @Inject constructor(): NetworkServiceFactory {

    @Volatile
    private var instance: OpenWeatherApi? = null

    override fun getServiceInstance(): OpenWeatherApi =
        instance ?: synchronized(this) {
            instance ?: buildNetworkService().also { instance = it }
        }

    private fun buildNetworkService(): OpenWeatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(OpenWeatherApi::class.java)


}