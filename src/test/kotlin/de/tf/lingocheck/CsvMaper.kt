package de.tf.lingocheck

import de.tf.lingocheck.util.UtilResources
import java.text.SimpleDateFormat
import java.util.*


fun Course.toCsv(): String {
    return commit.toString() + ";" + dateFormat.format(date) + ";" + classType + ";" + classTopicType + ";" + languageLevel + ";" + presentationNumber + ";" + classTopic
}

private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)

fun parseCsv(csv: String): Course {

    val values = csv.split(";")
    val commitUrl = UtilResources.getProperties("commitUrl")
    return Course(commit = values[0].toLong(),
            date = dateFormat.parse(values[1]),
            classType = values[2],
            classTopicType = values[3],
            languageLevel = values[4],
            presentationNumber = values[5],
            classTopic = values[6],
            url = commitUrl)
}