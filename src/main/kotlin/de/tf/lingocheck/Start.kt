package de.tf.lingocheck

suspend fun main() {
    //SearchCourses().findCoursesByCommitNumber()
    CalendarSearch().runFindCoursesByCalendar().join()
}
