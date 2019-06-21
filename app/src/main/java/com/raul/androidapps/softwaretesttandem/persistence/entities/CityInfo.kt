package com.raul.androidapps.softwaretesttandem.persistence.entities

import androidx.room.*
import com.raul.androidapps.softwaretesttandem.persistence.utils.Coordinates


@Entity(tableName = "city_info", indices = [(Index(value = arrayOf("id"), unique = true))])
data class CityInfo constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "country")
    var country: String/*,
    @Embedded
    val coordinates: Coordinates? = null*/
)



