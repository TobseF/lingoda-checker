package de.tf.lingocheck.by.api.url

import de.tf.lingocheck.Pusher
import de.tf.lingocheck.Whitelist
import de.tf.lingocheck.api.CsvMaperApiCourse
import de.tf.lingocheck.page.ClassCommitPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.util.BookingHistoryApi
import de.tf.lingocheck.util.ClassCounter
import de.tf.lingocheck.util.Configs
import java.util.concurrent.TimeUnit


class SearchCourses : TestBase() {

    fun findCoursesByCommitNumber() {
        HomePage(driver).login()
        print("Login successful")

        val commitUrlBase = Configs.commitUrl

        Thread.sleep(2000)
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)

        println("Start Searching Courses")

        var retry = 0
        while (true) {
            val classCounter = ClassCounter.getClassCount()
            val commitUrl = commitUrlBase + classCounter
            driver.navigate().to(commitUrl)
            Thread.sleep(1000)
            val commitPage = ClassCommitPage(driver)

            when {
                commitPage.isTakenPage() -> {
                    // println("Already taken: $commitCounterStart")
                    count()
                }
                commitPage.isMissingPage() -> {
                    //println("Missing page, lets sleep")
                    Thread.sleep(5000)
                }
                commitPage.isCommitPage() -> {
                    //print("Found class: $commitCounterStart")
                    val parseCourse = parseCourse(ClassCommitPage(driver), classCounter, commitUrl)
                    //print("Parsed class: $parseCourse")
                    nextCourse(parseCourse, commitPage)
                    count()
                }
                else -> {
                    if (retry >= 2) {
                        retry += 1
                    } else {
                        println("Skipped unpredicted page: \n" + commitUrl + "\n" + driver.pageSource)
                        retry = 0
                    }
                }
            }

        }
    }

    private fun count() {
        ClassCounter.count()
        println("Next Course to check: " + ClassCounter.getClassCount())
    }

    private fun nextCourse(course: ApiCourse, commitPage: ClassCommitPage) {
        if (course.isGerman()) {
            if (shouldBeBooked(course)) {
                commitPage.commit()
                println("Booked Course: $course")
                sendBookedCourse(course)
                BookingHistoryApi.bookCourse(course)
            } else {
                //println("New DE Course: $course")
                //sendNewCourse(course)
            }
        }
    }

    private fun shouldBeBooked(course: ApiCourse): Boolean {
        return (course.isGroupClass() || course.isPrivateClass()) && course.date.isAfter(course.date.plusHours(72)) && Whitelist.contains(
                course.date) && BookingHistoryApi.needsBooking(course)
    }

    private fun sendBookedCourse(parseCourse: ApiCourse) {
        sendCourse(parseCourse, "Booked-Course")
    }

    private fun sendNewCourse(parseCourse: ApiCourse) {
        sendCourse(parseCourse, "New-Course")
    }

    private fun sendCourse(parseCourse: ApiCourse, messageTitle: String) {
        val pusher = Pusher()
        pusher.send(messageTitle, CsvMaperApiCourse.toCsv(parseCourse))
    }

    data class CourseLink(val url: String, val topic: String) {
        fun getCommit() = url.substringAfterLast("/").toLong()
    }

}