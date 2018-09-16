package de.tf.lingocheck.test

import de.tf.lingocheck.parseDate
import org.testng.annotations.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals


class ParseDateTest {

    @Test
    fun parseDateTest() {

        assertEquals("19.09 09:00".toDateDe(), parseDate("Mi., Sep. 19, 09:00"))
        assertEquals("20.10 17:00".toDateDe(), parseDate("Sa., Okt. 20, 17:00"))
        assertEquals("04.10 20:00".toDateDe(), parseDate("Do., Okt. 04, 20:00"))
    }

    private fun String.toDateDe(): Date {
        val sampleDate = SimpleDateFormat("dd.MM HH:mm", Locale.GERMAN)
        return sampleDate.parse(this)!!
    }
}