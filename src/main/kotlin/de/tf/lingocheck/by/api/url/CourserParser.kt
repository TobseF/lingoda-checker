package de.tf.lingocheck.by.api.url

import de.tf.lingocheck.page.ClassCommitPage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


val formatter = DateTimeFormatter.ofPattern("yyyy MMM dd, HH:mm", Locale.GERMAN)

fun parseDate(dateAsText: String, defaultYear: String): LocalDateTime {
    val year = defaultYear + " " + dateAsText.trim().substringAfter(" ")
    return LocalDateTime.parse(year, formatter)!!
}

fun parseDate(dateAsText: String, now: LocalDateTime = LocalDateTime.now()): LocalDateTime {
    val currentYear = now.year
    val nextYear = currentYear + 1
    val dateA = parseDate(dateAsText, currentYear.toString())
    val dateB = parseDate(dateAsText, nextYear.toString())
    return if (dateA.isAfter(now)) {
        dateA
    } else {
        dateB
    }
}

fun Date.year(): Int {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal.get(Calendar.YEAR)
}

fun parseCourse(page: ClassCommitPage, commit: Long, url: String): ApiCourse {
    return ApiCourse(commit = commit,
            url = url,
            languageLevel = page.languageLevel.firstOrNull()?.text,
            classType = page.classType.firstOrNull()?.text,
            date = page.date.firstOrNull()?.text?.let { parseDate(it) }!!,
            classTopic = page.classTopic.firstOrNull()?.text,
            classTopicType = page.classTopicType.firstOrNull()?.text,
            presentationNumber = page.presentationNumber.firstOrNull()?.text)
}
