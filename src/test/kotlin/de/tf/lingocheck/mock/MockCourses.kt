package de.tf.lingocheck.mock

import de.tf.lingocheck.Course
import java.text.SimpleDateFormat
import java.util.*

private val sampleDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)

fun mockCourse() = Course(commit = 779390L,
        date = sampleDate.parse("21.11.2018 15:00"), url = "https://teacher.lingoda.com/teacher/classes/commit/779390",
        classTopicType = "Speaking",
        classTopic = "A conversation about shopping and spending money",
        classType = "Private class",
        languageLevel = "A2.2",
        presentationNumber = "A2_2038S_DE")