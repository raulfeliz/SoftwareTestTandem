package com.raul.androidapps.softwaretesttandem.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.raul.androidapps.softwaretesttandem.TandemApplication
import com.raul.androidapps.softwaretesttandem.model.ForecastResponse
import com.raul.androidapps.softwaretesttandem.model.TotalForecastResponse
import com.raul.androidapps.softwaretesttandem.model.WeatherResponse
import com.raul.androidapps.softwaretesttandem.network.NetworkServiceFactory
import com.raul.androidapps.softwaretesttandem.network.Resource
import com.raul.androidapps.softwaretesttandem.persistence.PersistenceManager
import com.raul.androidapps.softwaretesttandem.utils.TandemConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    app: TandemApplication,
    private val persistenceManager: PersistenceManager,
    private val networkServiceFactory: NetworkServiceFactory
) : AndroidViewModel(app) {

    private var lastTimeRequested: Long = 0
    private val serverResponse: MutableLiveData<Resource<TotalForecastResponse>> = MutableLiveData()

    fun createDb() {
        viewModelScope.launch {
            persistenceManager.createDb()
        }
    }

    fun getServerResponseObservable(): LiveData<Resource<TotalForecastResponse>> = serverResponse

    fun getForecast(id: Long) {
        serverResponse.value = Resource.loading()
        viewModelScope.launch {
            val currentWeatherResponse = async { getCurrentWeather(id) }
            val nextDaysWeatherResponse = async { getFiveDaysForecast(id) }
            withContext(Dispatchers.Main) {
                val currentWeather = currentWeatherResponse.await()
                val nextDaysWeather = nextDaysWeatherResponse.await()
                val errorMessage = when {
                    currentWeather.status == Resource.Status.ERROR -> currentWeather.message
                    nextDaysWeather.status == Resource.Status.ERROR -> nextDaysWeather.message
                    else -> null
                }
                serverResponse.value = if (errorMessage != null) {
                    Resource.error(errorMessage)
                } else {
                    Resource.success(TotalForecastResponse(currentWeather.data, nextDaysWeather.data))
                }
            }
        }
    }

    @VisibleForTesting
    suspend fun getCurrentWeather(id: Long): Resource<WeatherResponse> =
        withContext(Dispatchers.IO) {
            val response = networkServiceFactory.getServiceInstance().getCurrentWeather(id)
            if (response.isSuccessful.not()) {
                Resource.error(response.message())
            } else {
                Resource.success(response.body())
            }
        }


    @VisibleForTesting
    suspend fun getFiveDaysForecast(id: Long): Resource<ForecastResponse> =
        withContext(Dispatchers.IO) {
            val response = networkServiceFactory.getServiceInstance().getFiveDayForecast(id)
            if (response.isSuccessful.not()) {
                Resource.error(response.message())
            } else {
                Resource.success(response.body())
            }
        }

    fun needToRequestNewInfo(time: Long): Boolean =
        (time - lastTimeRequested > TandemConstants.TIME_TO_AVOID_NEXT_REQUEST)
            .also { if(it)  lastTimeRequested = time}



}


