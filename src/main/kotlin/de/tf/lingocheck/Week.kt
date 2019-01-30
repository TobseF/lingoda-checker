package de.tf.lingocheck

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Week(start: LocalDate) {

    val start = getWeek(start)

    companion object {
        fun getWeek(date: LocalDate): LocalDate = date.minusDays(date.dayOfWeek.value - 1L)
    }

    fun urlDate() = DateTimeFormatter.ofPattern("yyyy-MM-DD").format(start)

    fun last() = start.plusDays(6)

    fun contains(date: LocalDate) = date == start || date == last() || (date.isAfter(start) && date.isBefore(last()))

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

fun listWeeks(dates: List<LocalDateTime>): Set<Week> {
    return dates.toDates().toWeeks()
}

fun List<LocalDate>.toWeeks(): Set<Week> {
    return this.map { Week(it) }.toSet()
}

fun List<LocalDateTime>.toDates(): List<LocalDate> {
    return this.map { it.toLocalDate() }
}

