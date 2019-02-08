package de.tf.lingocheck.util

import de.tf.lingocheck.by.api.url.BookedCourse
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime

open class BookingHistory<T : BookedCourse>(val parser: (String) -> T, val writer: (T) -> String) {
    private var courses: MutableList<T>
    private val bookedFile = "book-history.txt"

    init {
        courses = File(bookedFile).readLines().map {
            parser.invoke(it)
        }.toMutableList()
    }

    fun bookCourse(course: T) {
        courses.add(course)
        write()
    }

    fun needsBooking(course: T): Boolean = !isAlreadyBooked(course)

    fun isAlreadyBooked(course: T): Boolean {
        return isAlreadyBooked(course.date)
    }

    fun isAlreadyBooked(course: LocalDateTime): Boolean {
        return courses.any { course == it.date }
    }

    private fun write() {
        courses.sortBy { it.date }
        val fileWriter = FileWriter(File(bookedFile))
        courses.forEach { fileWriter.appendln(writer.invoke(it)) }
        fileWriter.close()
    }

}