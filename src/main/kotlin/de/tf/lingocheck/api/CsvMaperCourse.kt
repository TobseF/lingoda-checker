package de.tf.lingocheck.api

import de.tf.lingocheck.by.api.url.Course

object CsvMaperCourse {

    fun toCsv(course: Course): String {
        return course.date.formatForCsv() + ";" + course.classType + ";" + course.classTopic
    }

    fun parseCsv(csv: String): Course {
        val values = csv.split(";")
        return Course(date = values[0].parseDateFromCsv(), classType = values[1], classTopic = values[2])
    }
}
