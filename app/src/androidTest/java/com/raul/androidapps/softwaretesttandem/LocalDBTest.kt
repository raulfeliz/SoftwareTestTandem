package com.raul.androidapps.softwaretesttandem

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.raul.androidapps.softwaretesttandem.model.City
import com.raul.androidapps.softwaretesttandem.persistence.daos.CityInfoDao
import com.raul.androidapps.softwaretesttandem.persistence.databases.TandemDatabase
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDBTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TandemDatabase
    private lateinit var cityInfoDao: CityInfoDao

    @Before
    @Throws(Exception::class)
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            TandemDatabase::class.java
        ).allowMainThreadQueries().build() // allowing main thread queries, just for testing

        cityInfoDao = database.cityInfoDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        database.close()
    }

    private fun getNameToSearch(name: String): String = "%${name}%"

    @Test
    @Throws(InterruptedException::class)
    fun getCityNull() {
        runBlocking {
            val city = cityInfoDao.getOneCity()
            assertNull(city)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun getOneMatch() {
        runBlocking {
            val city1 = City(id = 100, name = "valladolid", country = "Spain")
            val city2 = City(id = 102, name = "vallaldolor", country = "Spain")
            val cityEntity1 = CityInfoEntity.fromCityInfo(cityInfo = city1)
            val cityEntity2 = CityInfoEntity.fromCityInfo(cityInfo = city2)
            cityInfoDao.insert(listOf(cityEntity1, cityEntity2))
            val storedCities = cityInfoDao.getCityByName(getNameToSearch("valladolid"))
            assertTrue(storedCities.size == 1)
            assertEquals(storedCities.first(), cityEntity1)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun getTwoMatches() {
        runBlocking {
            val city1 = City(id = 100, name = "valladolid", country = "Spain")
            val city2 = City(id = 102, name = "vallaldolor", country = "Spain")
            val cityEntity1 = CityInfoEntity.fromCityInfo(cityInfo = city1)
            val cityEntity2 = CityInfoEntity.fromCityInfo(cityInfo = city2)
            cityInfoDao.insert(listOf(cityEntity1, cityEntity2))
            val storedCities = cityInfoDao.getCityByName(getNameToSearch("valla"))
            assertTrue(storedCities.size == 2)
        }
    }

}