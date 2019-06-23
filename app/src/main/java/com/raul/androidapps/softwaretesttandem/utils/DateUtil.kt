package com.raul.androidapps.softwaretesttandem.utils

import java.text.SimpleDateFormat
import java.util.*


object DateUtil {

    fun isSameDay(date1: Date?, date2: Date?): Boolean {
        if (date1 == null || date2 == null) {
            return false
        }
        val cal1 = Calendar.getInstance()
        cal1.time = date1
        val cal2 = Calendar.getInstance()
        cal2.time = date2
        return isSameDay(cal1, cal2)
    }

    fun isSameDay(cal1: Calendar?, cal2: Calendar?): Boolean {
        if (cal1 == null || cal2 == null) {
            return false
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

    fun dateByAdding(date: Date, days: Int, weeks: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        val daysToAdd = days + weeks * 7
        cal.add(Calendar.DATE, daysToAdd)
        return cal.time
    }

    fun getDay(date: Date): String =
        try {
            SimpleDateFormat("dd/MM/yyyy", Locale.UK).format(date)
        } catch (e: Exception) {
            ""
        }

    fun getTime(date: Date): String =
        try {
            SimpleDateFormat("HH:mm", Locale.UK).format(date)
        } catch (e: Exception) {
            ""
        }


}