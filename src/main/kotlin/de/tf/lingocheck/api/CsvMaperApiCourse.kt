package de.tf.lingocheck.api

import de.tf.lingocheck.by.api.url.ApiCourse
import de.tf.lingocheck.util.Configs

object CsvMaperApiCourse {

    fun toCsv(it: ApiCourse): String {
        return it.commit.toString() + ";" + it.date.formatForCsv() + ";" + it.classType + ";" + it.classTopicType + ";" + it.languageLevel + ";" + it.presentationNumber + ";" + it.classTopic
    }

    fun parseCsv(csv: String): ApiCourse {

        val values = csv.split(";")
        val commitUrl = Configs.getProperty("commitUrl")
        return ApiCourse(commit = values[0].toLong(),
                date = values[1].parseDateFromCsv(),
                classType = values[2],
                classTopicType = values[3],
                languageLevel = values[4],
                presentationNumber = values[5],
                classTopic = values[6],
                url = commitUrl + values[0])
    }
}
