package com.raul.androidapps.softwaretesttandem.model

import com.raul.androidapps.softwaretesttandem.utils.DateUtil
import java.util.*


class FiveDaysForecast constructor(forecast: ForecastResponse) {

    private val forecastList: MutableList<List<DayForecast>> = mutableListOf()

    init {
        val refDate = getBiggerDate(forecast.list)
        val auxList: MutableList<Forecast> = forecast.list.toMutableList()
        var offset = 0
        while (auxList.isNotEmpty()) {
            val auxDate = DateUtil.dateByAdding(refDate, -offset, 0)
            val list = forecast.list.filter { DateUtil.isSameDay(Date(it.dt * 1000), auxDate) }
            DayForecast(
                DateUtil.getDay(auxDate),
                list
            )
            auxList.removeAll(list)
            offset++
            forecastList.add(list.map {
                DayForecast(
                    DateUtil.getDay(auxDate),
                    forecast.list.filter { DateUtil.isSameDay(Date(it.dt * 1000), auxDate) })
            })
        }
    }

    private fun getBiggerDate(list: List<Forecast>): Date {
        val orderedList = list.sortedByDescending { it.dt }
        orderedList.firstOrNull()?.dt?.let { date ->
            return Date(date * 1000)
        }
        return Date()
    }

    fun getList(): List<List<DayForecast>> = forecastList.toList()

    class DayForecast constructor(val date: String, val forecast: List<Forecast>)

}