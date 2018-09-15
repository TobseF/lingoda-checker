package de.tf.lingocheck

data class Course(val url: String?,
        val languageLevel: String?,
        val classType: String?,
        val date: String?,
        val classTopic: String?,
        val classTopicType: String?,
        val learningOutcome: String?) {

    override fun toString(): String {
        return "$date - $classType ($languageLevel): [$classTopicType] - $classTopic \n $url"
    }
}