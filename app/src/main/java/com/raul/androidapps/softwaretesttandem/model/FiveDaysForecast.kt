package com.raul.androidapps.softwaretesttandem.model

import androidx.annotation.VisibleForTesting
import com.raul.androidapps.softwaretesttandem.utils.DateUtil
import java.util.*


object FiveDaysForecast {

    fun getAsList(forecast: ForecastResponse?): List<DayForecast> {
        forecast ?: return listOf()
        val forecastList: MutableList<DayForecast> = mutableListOf()
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
        return forecastList.toList()

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


}