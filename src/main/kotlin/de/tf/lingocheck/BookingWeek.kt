package de.tf.lingocheck

import de.tf.lingocheck.util.Configs
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields


class BookingWeek(val week: Week, val courses: MutableList<LocalDateTime>) : Week(week.start) {

    companion object {
        private var toStringFormatterDate = DateTimeFormatter.ofPattern("EEE dd.MM HH:mm")
        private fun LocalDate.week(): Int = this.get(WeekFields.ISO!!.weekOfWeekBasedYear())
        private fun LocalDateTime.formatted(): String = toStringFormatterDate.format(this)
    }

    fun getDateUrl(): String {
        val teacherUrl = Configs.teacherUrl
        val classesUrl = teacherUrl + "classes/"
        return classesUrl + week.urlDate()
    }

    override fun toString(): String {
        return "Week ${startDate.week()}: " + courses.map { it.formatted() }
    }

}