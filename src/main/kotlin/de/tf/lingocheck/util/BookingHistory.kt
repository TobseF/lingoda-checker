package de.tf.lingocheck.util

import de.tf.lingocheck.Course
import de.tf.lingocheck.parseCsv
import de.tf.lingocheck.toCsv
import java.io.File
import java.io.FileWriter

object BookingHistory {
    private var courses: MutableList<Course>
    private const val bookedFile = "book-history.txt"

    init {
        courses = File(bookedFile).readLines().map {
            parseCsv(it)
        }.toMutableList()
    }

    fun bookCourse(course: Course) {
        courses.add(course)
        write()
    }

    fun needsBooking(course: Course): Boolean = !isAlreadyBooked(course)

    fun isAlreadyBooked(course: Course): Boolean {
        return courses.any { course.date == it.date }
    }

    private fun write() {
        courses.sortBy { it.date.time }
        val fileWriter = FileWriter(File(bookedFile))
        courses.forEach { fileWriter.appendln(it.toCsv()) }
        fileWriter.close()
    }

}