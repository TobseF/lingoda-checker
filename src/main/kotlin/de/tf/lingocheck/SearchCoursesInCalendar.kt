package de.tf.lingocheck

import de.tf.lingocheck.page.ClassesPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.util.Configs
import de.tf.lingocheck.util.TestBase


class SearchCoursesInCalendar : TestBase() {

    fun findCoursesByCalendar() {
        val classesPageAfterLogin = HomePage(driver).login()
        println("Login successful")
        println("Hallo " + classesPageAfterLogin.getUserName())


        val weeks = listBookingWeeks(Whitelist.getUnbooked())
        val firstWeek = weeks.first()
        val date = firstWeek.start

        openPage(getDateUrl(firstWeek))

        val classesPage = ClassesPage(driver)
        val courses = classesPage.findCoursesByDays(date)
        val coursesTooBook = courses.filter { course -> firstWeek.contains(course.date) }
                .filter { course -> Whitelist.containsUnbooked(course.date) }
        coursesTooBook.forEach { it.commit() }

        println(coursesTooBook)
    }

    fun openPage(url: String) {
        driver.get(url)
    }

    private fun getDateUrl(firstWeek: BookingWeek): String {
        val teacherUrl = Configs.getProperty("teacherURL")
        val classesUrl = teacherUrl + "classes/"
        return classesUrl + firstWeek.urlDate()
    }


}