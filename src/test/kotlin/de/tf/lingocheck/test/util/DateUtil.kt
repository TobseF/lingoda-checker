package de.tf.lingocheck.test.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder


fun String.toDate(): LocalDateTime {
    return LocalDateTime.parse(this, formatter)
}

private val formatter by lazy { getDateTimeFormatter() }

private fun getDateTimeFormatter(): DateTimeFormatter? {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    return DateTimeFormatterBuilder().parseLenient().append(formatter).toFormatter()
}

fun dateList(vararg dates: String) = dates.map { it.toDate() }

fun dateSet(vararg dates: String) = dateList(*dates).toSet()