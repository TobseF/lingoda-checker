package de.tf.lingocheck.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

fun LocalDateTime.getFormattedDate(): String {
    val sampleDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
    return sampleDate.format(this)
}

private var toStringFormatterDate = DateTimeFormatter.ofPattern("EEE dd.MM HH:mm", Locale.GERMANY)
fun LocalDateTime.formatted(): String = toStringFormatterDate.format(this)
fun LocalDate.week(): Int = this.get(WeekFields.ISO!!.weekOfWeekBasedYear())