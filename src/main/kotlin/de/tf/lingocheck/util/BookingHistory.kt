package de.tf.lingocheck.util

import de.tf.lingocheck.api.parseCsv
import de.tf.lingocheck.api.toCsv
import de.tf.lingocheck.by.api.url.ApiCourse
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime

object BookingHistory {
    private var courses: MutableList<ApiCourse>
    private const val bookedFile = "book-history.txt"

    init {
        courses = File(bookedFile).readLines().map {
            parseCsv(it)
        }.toMutableList()
    }

    fun bookCourse(course: ApiCourse) {
        courses.add(course)
        write()
    }

    fun needsBooking(course: ApiCourse): Boolean = !isAlreadyBooked(course)

    fun isAlreadyBooked(course: ApiCourse): Boolean {
        return isAlreadyBooked(course.date)
    }

    fun isAlreadyBooked(course: LocalDateTime): Boolean {
        return courses.any { course == it.date }
    }

    private fun write() {
        courses.sortBy { it.date }
        val fileWriter = FileWriter(File(bookedFile))
        courses.forEach { fileWriter.appendln(it.toCsv()) }
        fileWriter.close()
    }

}