package de.tf.lingocheck.test

import de.tf.lingocheck.Week
import de.tf.lingocheck.listWeeks
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class WeekTest {

    @Test
    fun weekDayTestTuesday() {
        val date = LocalDate.of(2019, 1, 29)
        assertEquals(date.dayOfWeek, DayOfWeek.TUESDAY)
        val week = Week.getWeek(date)
        assertEquals(LocalDate.of(2019, 1, 28), week)
    }

    @Test
    fun weekDayTestMonday() {
        val date = LocalDate.of(2019, 1, 28)
        assertEquals(date.dayOfWeek, DayOfWeek.MONDAY)
        val week = Week.getWeek(date)
        assertEquals(date, week)
    }

    @Test
    fun weekDayTestSunday() {
        val date = LocalDate.of(2019, 1, 27)
        assertEquals(date.dayOfWeek, DayOfWeek.SUNDAY)
        val week = Week.getWeek(date)
        assertEquals(LocalDate.of(2019, 1, 21), week)
    }

    @Test
    fun weekUrlTest() {
        val date = LocalDate.of(2019, 1, 28)
        val week = Week(date)
        assertEquals("2019-01-28", week.urlDate())
    }

    @Test
    fun weekTest() {
        val date = LocalDate.of(2019, 1, 28)
        val week = Week(date)

        assertTrue(week.contains(LocalDate.of(2019, 1, 28)))
        assertTrue(week.contains(LocalDate.of(2019, 1, 29)))
        assertTrue(week.contains(LocalDate.of(2019, 2, 3)))

        assertFalse(week.contains(LocalDate.of(2019, 2, 4)))
        assertFalse(week.contains(LocalDate.of(2019, 1, 27)))
    }


    @Test
    fun listWeeksTest() {
        val mondays = listOf(LocalDate.of(2019, 1, 14), LocalDate.of(2019, 1, 21), LocalDate.of(2019, 1, 28))
        val weekDays = listWeeks(mondays.toDateTimes()).map { it.start }
        assertEquals(mondays, weekDays)
    }

    @Test
    fun listWeeksTest2() {
        val mondays = listOf(LocalDate.of(2019, 1, 14), LocalDate.of(2019, 1, 21), LocalDate.of(2019, 1, 28))
        val courseDays = listOf(LocalDate.of(2019, 1, 14).atTime(14, 30),
                LocalDate.of(2019, 1, 14).atTime(16, 30),
                LocalDate.of(2019, 1, 15).atTime(16, 30),
                LocalDate.of(2019, 1, 21).atTime(14, 30),
                LocalDate.of(2019, 1, 22).atTime(14, 30),
                LocalDate.of(2019, 1, 28).atTime(14, 30))
        val weekDays = listWeeks(courseDays).map { it.start }
        assertEquals(mondays, weekDays)
    }


    private fun List<LocalDate>.toDateTimes(): List<LocalDateTime> {
        return this.map { it.atLunchTime() }
    }

    private fun LocalDate.atLunchTime(): LocalDateTime = this.atTime(12, 30)

}