package com.raul.androidapps.softwaretesttandem.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity


@Dao
abstract class CityInfoDao : BaseDao<CityInfoEntity>() {

    @Query("SELECT * FROM city_info WHERE name LIKE :name")
    abstract suspend fun getCityByName(name: String): List<CityInfoEntity>

    @Query("SELECT * FROM city_info")
    abstract suspend fun getOneCity(): CityInfoEntity?


}