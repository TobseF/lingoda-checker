package de.tf.lingocheck

import de.tf.lingocheck.by.api.url.Course
import de.tf.lingocheck.page.ClassesPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.page.element.CourseCard
import de.tf.lingocheck.util.BookingHistoryCourse
import de.tf.lingocheck.util.Configs
import de.tf.lingocheck.util.createDriver
import org.openqa.selenium.WebDriver


class SearchCoursesInCalendar {

    fun findCoursesByCalendar() {

        val weeks = listBookingWeeks(Whitelist.getUnbooked())

        val courses = weeks.map { CourseSearch(it) }.toMutableList()

        while (courses.isNotEmpty()) {
            courses.forEach { course ->
                course.apply {
                    searchCourses()
                    refresh()
                }
            }
            courses.removeIf { it.hasNoCoursesToBook() }
        }
    }

    class CourseSearch(val week: BookingWeek) {
        var driver: WebDriver = createDriver()
        var loggedIn = false

        init {
            driver.get(Configs.pageURL)
        }

        fun searchCourses() {
            if (!loggedIn) {
                login()
            }
            val classesPage = ClassesPage(driver)
            val courses = classesPage.findCoursesByDays(week.start)
            val coursesTooBook = courses.filter(this::isCourseToBook)
            coursesTooBook.forEach { bookCourse(it) }
        }

        fun hasNoCoursesToBook() = week.courses.isEmpty()

        private fun bookCourse(course: CourseCard) {
            println("Booking course: $course")
            course.commit()
            BookingHistoryCourse.bookCourse(course.toCourse())
            week.courses.remove(course.date)
        }

        private fun CourseCard.toCourse() = Course(date = date, classTopic = topic, classType = type)

        private fun isCourseToBook(course: CourseCard) = course.isInWeek() && course.isOnCourseList()

        private fun CourseCard.isInWeek() = week.contains(this.date)

        private fun CourseCard.isOnCourseList() = Whitelist.containsUnbooked(this.date)

        fun close() {
            driver.close()
        }

        fun refresh() = driver.navigate().refresh()

        private fun login() {
            val classesPageAfterLogin = HomePage(driver).login()
            println("Login successful")
            println("Hallo " + classesPageAfterLogin.getUserName())
            driver.navigate().to(week.getDateUrl())
            loggedIn = true
        }

    }


}