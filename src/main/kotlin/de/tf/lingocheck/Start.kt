package de.tf.lingocheck

suspend fun main() {
    //SearchCourses().findCoursesByCommitNumber()
    SearchCoursesInCalendar().runFindCoursesByCalendar().join()
}
