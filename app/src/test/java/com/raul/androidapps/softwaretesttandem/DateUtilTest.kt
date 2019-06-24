package com.raul.androidapps.softwaretesttandem

import com.raul.androidapps.softwaretesttandem.utils.DateUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*


class DateUtilTest {
    @Test
    fun isSomeDay() {
        //get date
        val date: Calendar = GregorianCalendar(2011, Calendar.JULY, 3)
        //add 12 hours
        val datePlusTwelve: Calendar = GregorianCalendar(2011, Calendar.JULY, 3, 12, 0)

        assertTrue(DateUtil.isSameDay(date, datePlusTwelve))
        assertTrue(DateUtil.isSameDay(date.time, datePlusTwelve.time))
    }

    @Test
    fun dateByAdding() {
        //get date
        val date: Calendar = GregorianCalendar(2011, Calendar.JULY, 3)
        //FromCalendar
        val datePlusOneDay: Calendar = GregorianCalendar(2011, Calendar.JULY, 4)
        val datePlusOneWeek: Calendar = GregorianCalendar(2011, Calendar.JULY, 10)

        //From DateUtil
        val datePlusOneDay2 = DateUtil.dateByAdding(date.time, 1, 0)
        val datePlusOneWeek2 = DateUtil.dateByAdding(date.time, 0, 1)
        assertEquals(datePlusOneDay.time.time, datePlusOneDay2.time)
        assertEquals(datePlusOneWeek.time.time, datePlusOneWeek2.time)
    }


    @Test
    fun getDayAndTime() {
        //get date
        val date: Date = GregorianCalendar(2011, Calendar.JULY, 3, 12, 21).time

        assertEquals("03/07/2011", DateUtil.getDay(date))
        assertEquals("12:21", DateUtil.getTime(date))
    }



}
