package de.tf.lingocheck

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

open class Week(start: LocalDate) {

    val start = getWeek(start)

    companion object {
        fun getWeek(date: LocalDate): LocalDate = date.minusDays(date.dayOfWeek.value - 1L)
    }

    fun urlDate(): String = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(start)

    fun last(): LocalDate = start.plusDays(6)

    fun contains(date: LocalDate) = date == start || date == last() || (date.isAfter(start) && date.isBefore(last()))

    fun contains(date: LocalDateTime): Boolean {
        val start = LocalDateTime.of(this.start, LocalTime.MIN)
        val last = LocalDateTime.of(last(), LocalTime.MAX)
        return date.isAfter(start) && date.isBefore(last)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Week

        if (start != other.start) return false

        return true
    }

    override fun hashCode(): Int {
        return start.hashCode()
    }
}

fun listBookingWeeks(dates: Collection<LocalDateTime>): List<BookingWeek> {
    return listWeeks(dates).map { week ->
        BookingWeek(week, dates.filter { date -> week.contains(date) }.toMutableList())
    }
}

fun listWeeks(dates: Collection<LocalDateTime>): Set<Week> {
    return dates.toDates().toWeeks()
}

fun Collection<LocalDate>.toWeeks(): Set<Week> {
    return this.map { Week(it) }.toSet()
}

fun Collection<LocalDateTime>.toDates(): List<LocalDate> {
    return this.map { it.toLocalDate() }
}

