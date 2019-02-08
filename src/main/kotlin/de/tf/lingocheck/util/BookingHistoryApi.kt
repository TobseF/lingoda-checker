package de.tf.lingocheck.util

import de.tf.lingocheck.api.CsvMaperApiCourse
import de.tf.lingocheck.by.api.url.ApiCourse

object BookingHistoryApi : BookingHistory<ApiCourse>(CsvMaperApiCourse::parseCsv, CsvMaperApiCourse::toCsv)