package de.tf.lingocheck.by.api.url

import de.tf.lingocheck.page.ClassCommitPage
import java.text.SimpleDateFormat
import java.util.*


val formatter = SimpleDateFormat("yyyy MMMM dd, HH:mm", Locale.GERMAN)

fun parseDate(dateAsText: String, defaultYear: String): Date {
    return formatter.parse(defaultYear + " " + dateAsText.replace(".", "").trim().substringAfter(" "))!!
}

fun parseDate(dateAsText: String, now: Date = Date()): Date {
    val currentYear = now.year()
    val nextYear = currentYear + 1
    val dateA = parseDate(dateAsText, currentYear.toString())
    val dateB = parseDate(dateAsText, nextYear.toString())
    return if (dateA.after(now)) {
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
