package de.tf.lingocheck

import de.tf.lingocheck.page.ClassesPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.util.TestBase


class SearchCoursesInCalendar : TestBase() {

    fun findCoursesByCalendar() {
        HomePage(driver).login()
        print("Login successful")

        val classesPage = ClassesPage(driver)

        val weeks = listBookingWeeks(Whitelist.getUnbooked())
        val firstWeek = weeks.first()

        val date = firstWeek.start
        val courses = classesPage.findCoursesByDays(date)

        val coursesTooBook = courses.filter { course -> firstWeek.contains(course.date) }
                .filter { course -> Whitelist.containsUnbooked(course.date) }
        println(coursesTooBook)
    }


}