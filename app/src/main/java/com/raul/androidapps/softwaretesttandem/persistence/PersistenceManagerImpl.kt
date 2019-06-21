package com.raul.androidapps.softwaretesttandem.persistence

import com.raul.androidapps.softwaretesttandem.persistence.databases.TandemDatabase
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfo
import com.rukiasoft.androidapps.cocinaconroll.persistence.PersistenceManager
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(private val db: TandemDatabase) : PersistenceManager {

    override suspend fun getCity(name: String): List<CityInfo> =
        db.cityInfoDao().getCityByName("%$name%")
}

