package de.tf.lingocheck

import de.tf.lingocheck.page.ClassCommitPage
import de.tf.lingocheck.util.UtilResources
import java.text.SimpleDateFormat
import java.util.*


val formatter = SimpleDateFormat("yyyy MMM. dd, HH:mm", Locale.GERMAN)
val defaultYear = UtilResources.getProperties("currentYear")

fun parseDate(dateAsText: String): Date {
    return formatter.parse(defaultYear + " " + dateAsText.substringAfter(" "))!!
}

fun parseCourse(page: ClassCommitPage, commit: Long, url: String): Course {
    return Course(commit = commit,
            url = url,
            languageLevel = page.languageLevel.firstOrNull()?.text,
            classType = page.classType.firstOrNull()?.text,
            date = page.date.firstOrNull()?.text?.let { parseDate(it) }!!,
            classTopic = page.classTopic.firstOrNull()?.text,
            classTopicType = page.classTopicType.firstOrNull()?.text,
            presentationNumber = page.presentationNumber.firstOrNull()?.text)
}
