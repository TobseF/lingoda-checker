package de.tf.lingocheck

import de.tf.lingocheck.util.Configs
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class BookingWeek(val week: Week, val courses: MutableList<LocalDateTime>) : Week(week.start) {

    companion object {
        private var toStringFormatter = DateTimeFormatter.ofPattern("HH:mm")
        private fun LocalDateTime.formatted(): String = toStringFormatter.format(this)
    }

    fun getDateUrl(): String {
        val teacherUrl = Configs.teacherUrl
        val classesUrl = teacherUrl + "classes/"
        return classesUrl + week.urlDate()
    }

    override fun toString(): String {
        return super.toString() + courses.map { it.formatted() }
    }

}