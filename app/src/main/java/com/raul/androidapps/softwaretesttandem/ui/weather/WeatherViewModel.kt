package com.raul.androidapps.softwaretesttandem.ui.weather

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.model.*
import com.raul.androidapps.softwaretesttandem.network.NetworkServiceFactory
import com.raul.androidapps.softwaretesttandem.network.Resource
import com.raul.androidapps.softwaretesttandem.persistence.PersistenceManager
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity
import com.raul.androidapps.softwaretesttandem.preferences.PreferencesConstants
import com.raul.androidapps.softwaretesttandem.preferences.PreferencesManager
import com.raul.androidapps.softwaretesttandem.resources.ResourcesManager
import com.raul.androidapps.softwaretesttandem.utils.TandemConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val resourcesManager: ResourcesManager,
    private val persistenceManager: PersistenceManager,
    private val networkServiceFactory: NetworkServiceFactory
) : ViewModel() {


    private var lastTimeRequested: Long = 0
    private val serverResponse: MutableLiveData<Resource<TotalForecastResponse>> = MutableLiveData()
    private val suggestions: MutableLiveData<List<CityInfoEntity>> = MutableLiveData()

    init {
        suggestions.value = listOf()
    }

    fun resetLastTimeRequested() {
        lastTimeRequested = 0
    }

    fun createDb(preferenceManager: PreferencesManager) {
        if (!preferenceManager.getBooleanFromPreferences(PreferencesConstants.PROPERTY_DB_POPULATED_WITH_SMALL_LIST) ||
            !preferenceManager.getBooleanFromPreferences(PreferencesConstants.PROPERTY_DB_POPULATED_WITH_SMALL_LIST)
        ) {
            viewModelScope.launch {
                persistenceManager.createDb()
            }
        }
    }

    fun getServerResponseObservable(): LiveData<Resource<TotalForecastResponse>> = serverResponse
    fun getSuggestionsObservable(): LiveData<List<CityInfoEntity>> = suggestions

    fun getForecast(id: Long) {
        serverResponse.value = Resource.loading()
        viewModelScope.launch {
            val currentWeatherResponse = async { getCurrentWeather(id) }
            val nextDaysWeatherResponse = async { getFiveDaysForecast(id) }
            withContext(Dispatchers.Main) {
                val currentWeather = currentWeatherResponse.await()
                val nextDaysWeather = nextDaysWeatherResponse.await()
                val errorMessage = when {
                    currentWeather.status == Resource.Status.ERROR -> resourcesManager.getString(R.string.generic_error)
                    nextDaysWeather.status == Resource.Status.ERROR -> resourcesManager.getString(R.string.generic_error)
                    else -> null
                }
                serverResponse.value = if (errorMessage != null) {
                    Resource.error(errorMessage)
                } else {
                    Resource.success(
                        getTotalForecastResponse(
                            currentWeather.data,
                            nextDaysWeather.data
                        )
                    )
                }
            }
        }
    }

    private suspend fun getTotalForecastResponse(
        currentWeather: WeatherResponse?,
        nextDaysWeather: ForecastResponse?
    ): TotalForecastResponse =
        withContext(Dispatchers.Default) {
            val fiveDaysForecastAsList = FiveDaysForecast.getAsList(nextDaysWeather)
            TotalForecastResponse(currentWeather, fiveDaysForecastAsList)
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
            .also { if (it) lastTimeRequested = time }

    fun getSuggestions(name: String) {
        if (name.isBlank()) {
            suggestions.value = listOf()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val list = persistenceManager.getCity(name)
            suggestions.postValue(list)
        }
    }


}


