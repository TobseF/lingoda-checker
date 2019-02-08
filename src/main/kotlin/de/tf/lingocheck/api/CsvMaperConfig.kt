package de.tf.lingocheck.api

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.GERMAN)

fun LocalDateTime.formatForCsv() = dateFormat.format(this)!!
fun String.parseDateFromCsv() = LocalDateTime.parse(this, dateFormat)!!

