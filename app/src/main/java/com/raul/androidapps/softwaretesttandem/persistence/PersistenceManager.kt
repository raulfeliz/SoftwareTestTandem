package com.raul.androidapps.softwaretesttandem.persistence

import com.raul.androidapps.softwaretesttandem.models.CityInfo
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity

interface PersistenceManager {

    suspend fun getCity(name: String): List<CityInfoEntity>
    suspend fun createDb()
    suspend fun insertCities(cities: List<CityInfo>)

}