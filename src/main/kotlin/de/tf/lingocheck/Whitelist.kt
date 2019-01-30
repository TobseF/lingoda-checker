package de.tf.lingocheck

import java.io.File
import java.nio.charset.Charset.defaultCharset
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object Whitelist {

    val whitelist = mutableSetOf<LocalDateTime>()

    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

    private const val counterFile = "calender-whitelist.txt"

    private fun read() {
        read(File(counterFile).readLines(defaultCharset()))
    }

    fun contains(date: LocalDateTime): Boolean {
        if (whitelist.isEmpty()) {
            read()
        }
        return whitelist.contains(date)
    }

    fun read(lines: List<String>) {
        lines.filter(::isDataLine).forEach(::addDateLine)
    }

    private fun isDataLine(line: String) = !isComment(line) && line.contains(".") && line.contains(":")

    private fun isComment(line: String) = line.startsWith("#")

    private fun addDateLine(line: String) {
        //01.12.2018 08:00
        //01.12.2018 13:00 - 19:00
        val parts = line.split(" - ")
        val date1String = parts[0]

        val date1 = parse(date1String)

        val dateTime = date1String.split(" ")

        var date2: LocalDateTime? = null

        if (parts.size > 1) {
            date2 = parse(dateTime[0] + " " + parts[1])
        }
        if (date2 == null) {
            date2 = date1
        }
        val toHours = Duration.between(date1, date2).toHours()

        for (hour in 0..toHours) {
            whitelist += date1.plusHours(hour)
        }
    }

    private fun parse(string: String) = LocalDateTime.parse(string, formatter)!!

    private fun format(dateTime: LocalDateTime) = formatter.format(dateTime)!!

    private fun formatDate(dateTime: Date) = format(convertToLocalDateViaInstant(dateTime))

    private fun convertToLocalDateViaInstant(dateToConvert: Date): LocalDateTime {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

}