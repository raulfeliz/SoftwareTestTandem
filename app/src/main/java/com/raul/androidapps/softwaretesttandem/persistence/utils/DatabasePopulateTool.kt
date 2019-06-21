package com.raul.androidapps.softwaretesttandem.persistence.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raul.androidapps.softwaretesttandem.models.CityInfo
import com.raul.androidapps.softwaretesttandem.persistence.daos.CityInfoDao
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity
import com.raul.androidapps.softwaretesttandem.preferences.PreferencesConstants
import com.raul.androidapps.softwaretesttandem.preferences.PreferencesManager
import com.raul.androidapps.softwaretesttandem.utils.AssetFileUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DatabasePopulateTool @Inject constructor(
    private val assetFileUtil: AssetFileUtil,
    private val preferencesManager: PreferencesManager
) {

    suspend fun populateDbWithSmallList(dao: CityInfoDao) {
        populateDbWithList(dao, "current.city.list.min.json")
        preferencesManager.setBooleanIntoPreferences(PreferencesConstants.PROPERTY_DB_POPULATED_WITH_SMALL_LIST, true)
    }
    suspend fun populateDbWithBigList(dao: CityInfoDao) {
        populateDbWithList(dao, "city.list.min.json")
        preferencesManager.setBooleanIntoPreferences(PreferencesConstants.PROPERTY_DB_POPULATED_WITH_BIG_LIST, true)
    }

    private suspend fun populateDbWithList(dao: CityInfoDao, fileName: String) {
        withContext(Dispatchers.IO) {
            //populate db
            val start = System.currentTimeMillis()
            val smallText = assetFileUtil.loadJSONFromAsset(fileName)
            val gson = Gson()
            val smallList = gson.fromJson<List<CityInfo>>(smallText, object : TypeToken<ArrayList<CityInfo>>() {}.type)
            dao.insert(smallList.map { CityInfoEntity.fromCityInfo(it) })
            val end = System.currentTimeMillis()
            Timber.d("rukia list inserted in ${end-start} ms")
        }
    }
}