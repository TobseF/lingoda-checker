package de.tf.lingocheck.test

import de.tf.lingocheck.util.isLaterThan
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DateTest {

    @Test
    fun parseDateTest() {
        val now = "16.09.2018 09:00".toDateDe()
        assertTrue("25.09.2018 09:00".isLaterThan(48, now), "Too late")
        assertFalse("17.09.2018 09:00".isLaterThan(48, now), "Too early")
        assertFalse("1.09.2018 09:00".isLaterThan(48, now), "Too early")
    }

    private fun String.isLaterThan(hours: Int, now: Date): Boolean {
        return this.toDateDe().isLaterThan(hours, now)
    }

    private fun String.toDateDe(): Date {
        val sampleDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
        return sampleDate.parse(this)!!
    }
}