package de.tf.lingocheck

import java.text.SimpleDateFormat
import java.util.*

data class Course(val commit: Long, val url: String,
        val languageLevel: String?, val classType: String?, val date: Date,
        val classTopic: String?, val classTopicType: String?, val presentationNumber: String?) {

    override fun toString(): String {
        return "$[commit] ${getFormattedDate()} - $classType ($languageLevel): $classTopicType - $classTopic \n $url"
    }

    fun isGroupClass() = classType?.equals("Group class") ?: false

    fun isPrivateClass() = classType?.equals("Private class") ?: false

    fun getFormattedDate(): String {
        val sampleDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN)
        return sampleDate.format(date)!!
    }

    fun isGerman() = presentationNumber?.endsWith("DE") ?: false

}