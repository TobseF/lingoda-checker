package de.tf.lingocheck.util

import de.tf.lingocheck.api.CsvMaperCourse
import de.tf.lingocheck.by.api.url.Course

object BookingHistoryCourse : BookingHistory<Course>(CsvMaperCourse::parseCsv, CsvMaperCourse::toCsv)