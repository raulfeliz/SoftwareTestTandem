package com.raul.androidapps.softwaretesttandem.persistence.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raul.androidapps.softwaretesttandem.persistence.daos.CityInfoDao
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfo
import com.raul.androidapps.softwaretesttandem.persistence.utils.DbConverters

@Database(entities = [(CityInfo::class)], exportSchema = false, version = 1)
@TypeConverters(DbConverters::class)
abstract class TandemDatabase : RoomDatabase() {
    abstract fun cityInfoDao(): CityInfoDao
}
