package de.tf.lingocheck.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

fun LocalDateTime.getFormattedDate(): String {
    val sampleDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
    return sampleDate.format(this)
}