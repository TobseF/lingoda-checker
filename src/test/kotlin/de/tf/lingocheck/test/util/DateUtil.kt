package de.tf.lingocheck.test.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toDate(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    return LocalDateTime.parse(this, formatter)
}

fun dateList(vararg dates: String) = dates.map { it.toDate() }

fun dateSet(vararg dates: String) = dateList(*dates).toSet()