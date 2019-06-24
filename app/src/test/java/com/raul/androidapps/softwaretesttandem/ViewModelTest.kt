package com.raul.androidapps.softwaretesttandem

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.raul.androidapps.softwaretesttandem.model.ForecastResponse
import com.raul.androidapps.softwaretesttandem.model.WeatherResponse
import com.raul.androidapps.softwaretesttandem.network.NetworkServiceFactory
import com.raul.androidapps.softwaretesttandem.network.OpenWeatherApi
import com.raul.androidapps.softwaretesttandem.network.Resource
import com.raul.androidapps.softwaretesttandem.persistence.PersistenceManager
import com.raul.androidapps.softwaretesttandem.resources.ResourcesManager
import com.raul.androidapps.softwaretesttandem.ui.weather.WeatherViewModel
import com.raul.androidapps.softwaretesttandem.utils.convertToString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


class ViewModelTest {

    @Mock
    lateinit var resourcesManager: ResourcesManager
    @Mock
    lateinit var persistenceManager: PersistenceManager
    @Mock
    lateinit var networkServiceFactory: NetworkServiceFactory
    @Mock
    lateinit var openWeatherApi: OpenWeatherApi

    lateinit var stringForecastResponse: String
    lateinit var forecastResponse: ForecastResponse
    lateinit var weatherResponse: WeatherResponse
    lateinit var viewModelTest: WeatherViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModelTest = WeatherViewModel(resourcesManager, persistenceManager, networkServiceFactory)

        val inputForecastResponse = this.javaClass.classLoader?.getResourceAsStream("five_days_forecast.json")
        stringForecastResponse = inputForecastResponse?.convertToString() ?: ""
        forecastResponse = Gson().fromJson(stringForecastResponse, ForecastResponse::class.java)

        val inputWeatherResponse = this.javaClass.classLoader?.getResourceAsStream("five_days_forecast.json")
        val stringWeatherResponse = inputWeatherResponse?.convertToString()
        weatherResponse = Gson().fromJson(stringWeatherResponse, WeatherResponse::class.java)

        `when`(networkServiceFactory.getServiceInstance()).thenReturn(
            openWeatherApi
        )


    }

    @Test
    fun fiveDaysForecastSuccess() {
        runBlocking(Dispatchers.IO) {
            `when`(openWeatherApi.getFiveDayForecast(2L)).thenReturn(
                Response.success(forecastResponse)
            )
            val response = viewModelTest.getFiveDaysForecast(2L)
            assertTrue(response.status == Resource.Status.SUCCESS)
            assertEquals(response.data?.city?.id, forecastResponse.city.id)
        }
    }

    @Test
    fun fiveDaysForecastError() {
        runBlocking(Dispatchers.IO) {
            `when`(openWeatherApi.getFiveDayForecast(2L)).thenReturn(
                Response.error(400, ResponseBody.create(null, ""))
            )

            val response = viewModelTest.getFiveDaysForecast(2L)
            assertTrue(response.status == Resource.Status.ERROR)
        }
    }

    @Test
    fun currentForecastSuccess() {
        runBlocking(Dispatchers.IO) {
            `when`(openWeatherApi.getCurrentWeather(2L)).thenReturn(
                Response.success(weatherResponse)
            )

            val response = viewModelTest.getCurrentWeather(2L)
            assertTrue(response.status == Resource.Status.SUCCESS)
            assertEquals(response.data?.name, weatherResponse.name)
        }
    }

    @Test
    fun currentDaysForecastError() {
        runBlocking(Dispatchers.IO) {
            `when`(openWeatherApi.getCurrentWeather(2L)).thenReturn(
                Response.error(400, ResponseBody.create(null, ""))
            )
            val response = viewModelTest.getCurrentWeather(2L)
            assertTrue(response.status == Resource.Status.ERROR)
        }
    }


}
