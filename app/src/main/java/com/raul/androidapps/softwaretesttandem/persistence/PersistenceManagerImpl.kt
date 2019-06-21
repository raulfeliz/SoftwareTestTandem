package com.raul.androidapps.softwaretesttandem.persistence

import com.raul.androidapps.softwaretesttandem.models.CityInfo
import com.raul.androidapps.softwaretesttandem.persistence.databases.TandemDatabase
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity
import com.raul.androidapps.softwaretesttandem.persistence.utils.DatabasePopulateTool
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(
    private val db: TandemDatabase
) : PersistenceManager {

    override suspend fun getCity(name: String): List<CityInfoEntity> =
        db.cityInfoDao().getCityByName("%$name%")

    override suspend fun createDb() {
        //dumb function called to trigger the callback in the database creation
        db.cityInfoDao().getOneCity()
    }

    override suspend fun insertCities(cities: List<CityInfo>) {
        db.cityInfoDao().insert(cities.map { CityInfoEntity.fromCityInfo(it) })
    }
}

