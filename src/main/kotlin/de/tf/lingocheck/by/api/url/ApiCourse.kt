package de.tf.lingocheck.by.api.url

import de.tf.lingocheck.util.getFormattedDate
import java.time.LocalDateTime

data class ApiCourse(val commit: Long,
        val url: String,
        val languageLevel: String?,
        val classType: String?,
        override val date: LocalDateTime,
        val classTopic: String?,
        val classTopicType: String?,
        val presentationNumber: String?) : BookedCourse {


    override fun toString(): String {
        return "[$commit] ${date.getFormattedDate()} - $classType ($languageLevel): $classTopicType - $classTopic \n $url"
    }

    fun isGroupClass() = classType?.equals("Group class") ?: false

    fun isPrivateClass() = classType?.equals("Private class") ?: false


    fun isGerman() = presentationNumber?.endsWith("DE") ?: false

}