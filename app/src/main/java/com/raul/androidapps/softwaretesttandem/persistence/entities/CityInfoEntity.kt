package com.raul.androidapps.softwaretesttandem.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.raul.androidapps.softwaretesttandem.models.CityInfo


@Entity(tableName = "city_info", indices = [(Index(value = arrayOf("id"), unique = true))])
data class CityInfoEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    var name: String
) {
    companion object {
        fun fromCityInfo(cityInfo: CityInfo): CityInfoEntity =
            CityInfoEntity(id = cityInfo.id, name = cityInfo.name)
    }
}



