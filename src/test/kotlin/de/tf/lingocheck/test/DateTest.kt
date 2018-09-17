package de.tf.lingocheck.test

import de.tf.lingocheck.util.isLaterThan
import org.testng.annotations.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DateTest {

    @Test
    fun parseDateTest() {

        assertTrue("25.09.2018 09:00".isLaterThan(48), "Too late")
        assertFalse("17.09.2018 09:00".isLaterThan(48), "Too early")
        assertFalse("1.09.2018 09:00".isLaterThan(48), "Too early")
    }

    private fun String.isLaterThan(hours: Int): Boolean {
        return this.toDateDe().isLaterThan(hours)
    }

    private fun String.toDateDe(): Date {
        val sampleDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
        return sampleDate.parse(this)!!
    }
}