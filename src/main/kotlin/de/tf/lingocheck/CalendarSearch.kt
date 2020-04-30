package de.tf.lingocheck

import de.tf.lingocheck.by.api.url.Course
import de.tf.lingocheck.page.ClassesPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.page.element.CourseCard
import de.tf.lingocheck.util.BookingHistoryCourse
import de.tf.lingocheck.util.Configs
import de.tf.lingocheck.util.createDriver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.openqa.selenium.WebDriver
import kotlin.system.measureTimeMillis


class CalendarSearch {

    companion object {
        val log = KotlinLogging.logger("CalendarSearch")
    }

    private var job: Job = Job()

    var started = false
        private set

    fun runFindCoursesByCalendar(): Job {
        job = GlobalScope.launch {
            findCoursesByCalendar()
        }
        return job
    }

    fun findCoursesByCalendar() {
        started = true

        val coursesToBook = Whitelist.getUnbooked()
        val weeks = listBookingWeeks(coursesToBook)
        val courses = weeks.map { CourseSearch(it) }.toMutableList()
        val initialCourses = courses.numberOfCourses()
        fun coursesTable() = courses.joinToString("\n")

        log.debug { "Starting search courses loop for $initialCourses courses: \n${coursesTable()}" }
        while (job.isActive && courses.isNotEmpty()) {
            log.debug { "Starting search for ${courses.numberOfCourses()}/$initialCourses courses " }
            log.debug { "Courses:\n ${coursesTable()}" }
            val time = measureTimeMillis {
                courses.forEach(this::searchCourse)
            }
            log.debug { "Search for ${courses.numberOfCourses()} courses took $time ms" }
            courses.removeIf { it.hasNoCoursesToBook() }
        }
    }

    private fun searchCourse(course: CourseSearch) {
        course.apply {
            searchCourses()
            refresh()
        }
    }

    fun List<CourseSearch>.numberOfCourses() = this.map { it.week.courses.size }.sum()

    fun isStarted() = !job.isCancelled && !job.isCompleted

    fun stop() {
        job.cancel()
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
            log.info { "Booking course: $course" }
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
            log.info { "Login successful" }
            log.info { "Hallo ${classesPageAfterLogin.getUserName()}" }
            driver.navigate().to(week.getDateUrl())
            loggedIn = true
        }

        override fun toString(): String {
            return week.toString()
        }

    }


}