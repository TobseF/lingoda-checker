package de.tf.lingocheck

import de.tf.lingocheck.page.ClassCommitPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.util.ClassCounter
import de.tf.lingocheck.util.TestBase
import de.tf.lingocheck.util.UtilResources
import de.tf.lingocheck.util.isLaterThan
import org.testng.annotations.Test
import java.util.concurrent.TimeUnit

class SearchCourses : TestBase() {

    @Test
    fun findCourses() {
        val homePage = HomePage(driver)
        val classesPage = homePage.login()

        val courseLinks = mutableListOf<CourseLink>()
        for (i in 1..4) {
            courseLinks.addAll(classesPage.findCoursesLinks())
            classesPage.buttonNextWeek?.click()
        }

        val courses = mutableListOf<Course>()
        for (link in courseLinks) {
            driver.navigate().to(link.url)
            courses += parseCourse(ClassCommitPage(driver), link.getCommit(), link.url)
        }

        if (courses.isEmpty()) {
            print("No free courses available")
        }
        courses.forEach { println(it) }
    }

    @Test
    fun findCoursesByCommitNumber() {
        HomePage(driver).login()

        val commitUrlBase = UtilResources.getProperties("commitUrl")

        Thread.sleep(2000)
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
        while (true) {
            val classCounter = ClassCounter.getClassCount()
            val commitUrl = commitUrlBase + classCounter
            driver.navigate().to(commitUrl)
            Thread.sleep(1000)
            val commitPage = ClassCommitPage(driver)

            when {
                commitPage.isTakenPage() -> {
                    // print("Already taken: $commitCounterStart")
                    ClassCounter.count()
                }
                commitPage.isMissingPage() -> {
                    println("Missing page, lets sleep")
                    Thread.sleep(5000)
                }
                commitPage.isCommitPage() -> {
                    //print("Found class: $commitCounterStart")
                    val parseCourse = parseCourse(ClassCommitPage(driver), classCounter, commitUrl)
                    //print("Parsed class: $parseCourse")
                    nextCourse(parseCourse, commitPage)
                    ClassCounter.count()
                }
                else -> {
                    throw IllegalStateException("Fund unpredicted page: \n" + commitUrl + "\n" + driver.pageSource)
                }
            }

        }
    }

    private fun nextCourse(course: Course, commitPage: ClassCommitPage) {
        if (course.isGerman()) {
            if (shouldBeBooked(course)) {
                commitPage.commit()
                println("Booked Course: $course")
                sendBookedCourse(course)
            } else {
                println("New DE Course: $course")
                sendNewCourse(course)
            }
        }
    }

    fun shouldBeBooked(course: Course): Boolean {
        return course.date.isLaterThan(hours = 72) && (course.isGroupClass() || course.isPrivateClass())
    }

    private fun sendBookedCourse(parseCourse: Course) {
        sendCourse(parseCourse, "Booked-Course")
    }

    private fun sendNewCourse(parseCourse: Course) {
        sendCourse(parseCourse, "New-Course")
    }

    private fun sendCourse(parseCourse: Course, messageTitle: String) {
        val pusher = Pusher()
        pusher.send(messageTitle, parseCourse.toCsv())
    }

    data class CourseLink(val url: String, val topic: String) {
        fun getCommit() = url.substringAfterLast("/").toLong()
    }

}