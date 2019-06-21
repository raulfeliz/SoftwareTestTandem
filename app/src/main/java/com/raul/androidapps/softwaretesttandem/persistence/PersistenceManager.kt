package com.rukiasoft.androidapps.cocinaconroll.persistence

import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfo

interface PersistenceManager {

    suspend fun getCity(name: String): List<CityInfo>

}