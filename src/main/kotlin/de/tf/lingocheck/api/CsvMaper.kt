package de.tf.lingocheck.api

import de.tf.lingocheck.by.api.url.ApiCourse
import de.tf.lingocheck.util.UtilResources
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


fun ApiCourse.toCsv(): String {
    return commit.toString() + ";" + dateFormat.format(date) + ";" + classType + ";" + classTopicType + ";" + languageLevel + ";" + presentationNumber + ";" + classTopic
}

private val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.GERMAN)

fun parseCsv(csv: String): ApiCourse {

    val values = csv.split(";")
    val commitUrl = UtilResources.getProperties("commitUrl")
    return ApiCourse(commit = values[0].toLong(), date = LocalDateTime.parse(values[1], dateFormat),
            classType = values[2],
            classTopicType = values[3],
            languageLevel = values[4],
            presentationNumber = values[5],
            classTopic = values[6],
            url = commitUrl + values[0])
}