package de.tf.lingocheck

import de.tf.lingocheck.by.api.url.ApiCourse
import de.tf.lingocheck.by.api.url.SearchCourses
import de.tf.lingocheck.by.api.url.TestBase
import de.tf.lingocheck.by.api.url.parseCourse
import de.tf.lingocheck.page.ClassCommitPage
import de.tf.lingocheck.page.HomePage

class IterateCalendar : TestBase() {

    fun findCourses() {
        val homePage = HomePage(driver)
        val classesPage = homePage.login()

        val courseLinks = mutableListOf<SearchCourses.CourseLink>()
        for (i in 1..4) {
            courseLinks.addAll(classesPage.findCoursesLinks())
            classesPage.buttonNextWeek?.click()
        }

        val courses = mutableListOf<ApiCourse>()
        for (link in courseLinks) {
            driver.navigate().to(link.url)
            courses += parseCourse(ClassCommitPage(driver), link.getCommit(), link.url)
        }

        if (courses.isEmpty()) {
            print("No free courses available")
        }
        courses.forEach { println(it) }
    }
}