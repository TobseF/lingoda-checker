package de.tf.lingocheck

import de.tf.lingocheck.util.Configs
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class BookingWeek(val week: Week, val courses: MutableList<LocalDateTime>) : Week(week.start) {

    companion object {
        private var toStringFormatter = DateTimeFormatter.ofPattern("HH:mm")
        private var toStringFormatterDate = DateTimeFormatter.ofPattern("EEE dd.MM")
        private fun LocalDateTime.formatted(): String = toStringFormatter.format(this)
        private fun LocalDate.formatted(): String = toStringFormatterDate.format(this)
    }

    fun getDateUrl(): String {
        val teacherUrl = Configs.teacherUrl
        val classesUrl = teacherUrl + "classes/"
        return classesUrl + week.urlDate()
    }

    override fun toString(): String {
        return startDate.formatted() + " " + courses.map { it.formatted() }
    }

}