package de.tf.lingocheck

import de.tf.lingocheck.page.ClassCommitPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.util.TestBase

class IterateCalendar : TestBase() {

    fun findCourses() {
        val homePage = HomePage(driver)
        val classesPage = homePage.login()

        val courseLinks = mutableListOf<SearchCourses.CourseLink>()
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
}