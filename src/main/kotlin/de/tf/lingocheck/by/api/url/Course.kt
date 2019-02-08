package de.tf.lingocheck.by.api.url

import de.tf.lingocheck.util.getFormattedDate
import java.time.LocalDateTime

data class Course(override val date: LocalDateTime, val classType: String, val classTopic: String) : BookedCourse {

    override fun toString(): String {
        return "Course(${date.getFormattedDate()}: $classType - $classTopic)"
    }

    fun isGroupClass() = classType == "Group class"

    fun isPrivateClass() = classType == "Private class"

}