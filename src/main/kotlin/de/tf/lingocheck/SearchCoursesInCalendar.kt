package de.tf.lingocheck

import de.tf.lingocheck.page.ClassesPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.util.TestBase
import java.time.LocalDate


class SearchCoursesInCalendar : TestBase() {

    fun findCoursesByCalendar() {
        HomePage(driver).login()
        print("Login successful")

        val classesPage = ClassesPage(driver)


        val date = LocalDate.of(2019, 1, 28)
        val courses = classesPage.findCourses(date)


        val weeks = listBookingWeeks(Whitelist.getUnbooked())
    }


}