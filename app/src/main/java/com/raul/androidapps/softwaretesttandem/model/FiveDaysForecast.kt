package com.raul.androidapps.softwaretesttandem.model

import androidx.annotation.VisibleForTesting
import com.raul.androidapps.softwaretesttandem.utils.DateUtil
import java.util.*


class FiveDaysForecast constructor(forecast: ForecastResponse) {

    private val forecastList: MutableList<DayForecast> = mutableListOf()

    init {
        val refDate = getClosestDate(forecast.list)
        val auxList: MutableList<Forecast> = forecast.list.toMutableList()
        var offset = 0
        while (auxList.isNotEmpty()) {
            val auxDate = DateUtil.dateByAdding(refDate, offset, 0)
            val list = forecast.list.filter { DateUtil.isSameDay(it.getDate(), auxDate) }
            DayForecast(
                DateUtil.getDay(auxDate),
                list
            )
            auxList.removeAll(list)
            offset++
            forecastList.add(
                DayForecast(
                    DateUtil.getDay(auxDate),
                    forecast.list.filter { DateUtil.isSameDay(it.getDate(), auxDate) })
            )
        }
    }

    companion object {
        @VisibleForTesting
        fun getClosestDate(list: List<Forecast>): Date {
            val orderedList = list.sortedBy { it.dt }
            orderedList.firstOrNull()?.let {
                return it.getDate()
            }
            return Date()
        }
    }

    fun getList(): List<DayForecast> = forecastList.toList()

    class DayForecast constructor(val date: String, val forecast: List<Forecast>)

}