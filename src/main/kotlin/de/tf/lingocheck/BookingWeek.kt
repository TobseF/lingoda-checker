package de.tf.lingocheck

import de.tf.lingocheck.util.Configs
import de.tf.lingocheck.util.formatted
import de.tf.lingocheck.util.week
import java.time.LocalDateTime


class BookingWeek(val week: Week, val courses: MutableList<LocalDateTime>) : Week(week.start) {

    fun getDateUrl(): String {
        val teacherUrl = Configs.teacherUrl
        val classesUrl = teacherUrl + "classes/"
        return classesUrl + week.urlDate()
    }

    override fun toString(): String {
        return "Week ${startDate.week()}: " + courses.map { it.formatted() }
    }

}