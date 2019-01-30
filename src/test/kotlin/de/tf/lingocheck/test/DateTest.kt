package de.tf.lingocheck.test

import de.tf.lingocheck.test.util.toDate
import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DateTest {

    @Test
    fun parseDateTest() {
        val now = "16.09.2018 09:00".toDate()
        assertTrue("25.09.2018 09:00".isAfter(48, now), "Too late")
        assertFalse("17.09.2018 09:00".isAfter(48, now), "Too early")
        assertFalse("1.09.2018 09:00".isAfter(48, now), "Too early")
    }

    private fun String.isAfter(hours: Int, now: LocalDateTime): Boolean {
        return this.toDate().isAfter(now.plusHours(hours.toLong()))
    }


}