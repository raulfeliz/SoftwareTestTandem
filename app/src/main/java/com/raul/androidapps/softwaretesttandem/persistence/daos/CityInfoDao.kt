package com.raul.androidapps.softwaretesttandem.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfo



@Dao
abstract class CityInfoDao : BaseDao<CityInfo>() {

    @Query("SELECT * FROM city_info WHERE name LIKE :name")
    abstract suspend fun getCityByName(name: String): List<CityInfo>


}