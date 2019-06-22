package com.raul.androidapps.softwaretesttandem.ui.main

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.raul.androidapps.softwaretesttandem.TandemApplication
import com.raul.androidapps.softwaretesttandem.network.NetworkServiceFactory
import com.raul.androidapps.softwaretesttandem.network.OpenWeatherApi
import com.raul.androidapps.softwaretesttandem.persistence.PersistenceManager
import com.raul.androidapps.softwaretesttandem.utils.TandemConstants
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    app: TandemApplication,
    private val persistenceManager: PersistenceManager,
    private val networkServiceFactory: NetworkServiceFactory
) : AndroidViewModel(app) {

    private var lastTimeRequested: Long = 0

    fun createDb() {
        viewModelScope.launch {
            persistenceManager.createDb()
        }
    }

    fun getFiveDaysForecast(id: Long){
        viewModelScope.launch {
            var result = networkServiceFactory.getServiceInstance().getFiveDayForecast(id)
            Timber.d("")
        }
    }

    fun needToRequesNewInfo(currentTime: Long): Boolean =
        currentTime - lastTimeRequested > TandemConstants.TIME_TO_AVOID_NEXT_REQUEST

}
