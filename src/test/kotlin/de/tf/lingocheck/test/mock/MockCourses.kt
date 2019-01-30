package de.tf.lingocheck.test.mock

import de.tf.lingocheck.by.api.url.ApiCourse
import de.tf.lingocheck.test.util.toDate

fun mockCourse() = ApiCourse(commit = 779390L, date = "21.11.2018 15:00".toDate(),
        url = "https://teacher.lingoda.com/teacher/classes/commit/779390",
        classTopicType = "Speaking",
        classTopic = "A conversation about shopping and spending money",
        classType = "Private class",
        languageLevel = "A2.2",
        presentationNumber = "A2_2038S_DE")