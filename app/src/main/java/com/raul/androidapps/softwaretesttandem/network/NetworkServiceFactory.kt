package com.raul.androidapps.softwaretesttandem.network


interface NetworkServiceFactory {


    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val TEMPLATE_ICON_URL = "http://openweathermap.org/img/w/%1s.png"

    }

    fun getServiceInstance(): OpenWeatherApi
}