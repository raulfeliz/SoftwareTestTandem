package com.raul.androidapps.softwaretesttandem

import com.google.gson.Gson
import com.raul.androidapps.softwaretesttandem.model.FiveDaysForecast
import com.raul.androidapps.softwaretesttandem.model.ForecastResponse
import com.raul.androidapps.softwaretesttandem.utils.DateUtil
import com.raul.androidapps.softwaretesttandem.utils.convertToString
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class FiveDaysForecastTest {

    private lateinit var forecastResponse: ForecastResponse

    @Before
    fun setUp() {

        val inputForecastResponse = this.javaClass.classLoader?.getResourceAsStream("five_days_forecast.json")
        val stringForecastResponse = inputForecastResponse?.convertToString() ?: ""
        forecastResponse = Gson().fromJson(stringForecastResponse, ForecastResponse::class.java)

    }

    @Test
    fun testClosestDate() {
        val refDate = GregorianCalendar(2017, Calendar.JANUARY, 30).time
        val closestDay = FiveDaysForecast.getClosestDate(forecastResponse.list)
        DateUtil.isSameDay(closestDay, refDate)
    }

    @Test
    fun testList() {
        val list = FiveDaysForecast.getAsList(forecastResponse)
        Assert.assertTrue(list.size == 6)

        val firstDayRef = GregorianCalendar(2017, Calendar.JANUARY, 30)
        val firstDay = list[0]
        Assert.assertTrue(firstDay.forecast.size == 2)
        Assert.assertTrue(DateUtil.isSameDay(firstDay.forecast.first().getDate(), firstDayRef.time))

        val secondDayRef = GregorianCalendar(2017, Calendar.JANUARY, 31)
        val secondDay = list[1]
        Assert.assertTrue(secondDay.forecast.size == 8)
        Assert.assertTrue(DateUtil.isSameDay(secondDay.forecast.first().getDate(), secondDayRef.time))

        val thirdDayRef = GregorianCalendar(2017, Calendar.FEBRUARY, 1)
        val thirdDay = list[2]
        Assert.assertTrue(thirdDay.forecast.size == 8)
        Assert.assertTrue(DateUtil.isSameDay(thirdDay.forecast.first().getDate(), thirdDayRef.time))

        val fouthDayRef = GregorianCalendar(2017, Calendar.FEBRUARY, 2)
        val fouthDay = list[3]
        Assert.assertTrue(fouthDay.forecast.size == 8)
        Assert.assertTrue(DateUtil.isSameDay(fouthDay.forecast.first().getDate(), fouthDayRef.time))

        val fifthDayRef = GregorianCalendar(2017, Calendar.FEBRUARY, 3)
        val fifthDay = list[4]
        Assert.assertTrue(fifthDay.forecast.size == 8)
        Assert.assertTrue(DateUtil.isSameDay(fifthDay.forecast.first().getDate(), fifthDayRef.time))

        val sixthDayRef = GregorianCalendar(2017, Calendar.FEBRUARY, 4)
        val sixthDay = list[5]
        Assert.assertTrue(sixthDay.forecast.size == 6)
        Assert.assertTrue(DateUtil.isSameDay(sixthDay.forecast.first().getDate(), sixthDayRef.time))


    }
}