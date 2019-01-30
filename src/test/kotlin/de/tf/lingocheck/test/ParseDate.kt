package de.tf.lingocheck.test

import de.tf.lingocheck.by.api.url.parseDate
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals


class ParseDateTest {

    @Test
    fun parseSpecialDateTest() {
        val now = "08.10 20:00 2018".toDateDe()
        assertEquals("08.03 17:00 2019".toDateDe(), parseDate("Fr., März 08, 17:00", now))
        assertEquals("05.04 17:00 2019".toDateDe(), parseDate("Fr., Apr. 05, 17:00", now))
    }

    @Test
    fun parseDateTest() {
        val now = "01.09 00:00 2018".toDateDe()
        assertEquals("19.09 09:00 2018".toDateDe(), parseDate("Mi., Sep. 19, 09:00", now))
        assertEquals("20.10 17:00 2018".toDateDe(), parseDate("Sa., Okt. 20, 17:00", now))
        assertEquals("04.10 20:00 2018".toDateDe(), parseDate("Do., Okt. 04, 20:00", now))
        assertEquals("04.10 20:00 2018".toDateDe(), parseDate("Do., Okt. 04, 20:00", now))

        assertEquals("29.09 09:00 2018".toDateDe(), parseDate("Sa., Sep. 29, 09:00", now))
    }

    @Test
    fun parseDateNextYearTest() {
        val now = "01.11 00:00 2018".toDateDe()
        assertEquals("02.01 09:00 2019".toDateDe(), parseDate("Mi., Jan. 02, 09:00", now))
        assertEquals("01.03 14:00 2019".toDateDe(), parseDate("Fr., März 01, 14:00", now))
    }

    private fun String.toDateDe(): Date {
        val sampleDate = SimpleDateFormat("dd.MM HH:mm yyyy", Locale.GERMAN)
        return sampleDate.parse(this)!!
    }
}