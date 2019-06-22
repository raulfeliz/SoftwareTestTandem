package com.raul.androidapps.softwaretesttandem.persistence

import com.raul.androidapps.softwaretesttandem.model.City
import com.raul.androidapps.softwaretesttandem.persistence.databases.TandemDatabase
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity
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

    override suspend fun insertCities(cities: List<City>) {
        db.cityInfoDao().insert(cities.map { CityInfoEntity.fromCityInfo(it) })
    }
}

