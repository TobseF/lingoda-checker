package de.tf.lingocheck

import com.github.kittinunf.fuel.Fuel
import de.tf.lingocheck.page.ClassCommitPage
import de.tf.lingocheck.page.HomePage
import de.tf.lingocheck.util.TestBase
import org.testng.annotations.Test

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
            courses += parseCourse(ClassCommitPage(driver), link)
        }

        if (courses.isEmpty()) {
            print("No free courses available")
        }
        courses.forEach { println(it) }

    }

    @Test
    fun tesFuel() {
        val url = "https://1aeff5d8-d964-4db6-a07a-75dfecd7bcb9.pushnotifications.pusher.com/publish_api/v1/instances/1aeff5d8-d964-4db6-a07a-75dfecd7bcb9/publishes"
        val json = """
            {
              "interests": [
                "hello"
              ],
              "fcm": {
                "notification": {
                  "title": "Hello J",
                  "body": "Hello, Java!"
                }
              }
            }
        """.trimIndent()
        Fuel.post(url).body(json).header(mapOf("Content-Type" to "application/json"))
                .header(mapOf("Authorization" to "Bearer 014C90C7026AAAFE075E02409D9F55C8CB103E2EFFDEC9318A523F73C614D15C"))
    }

    private fun parseCourse(page: ClassCommitPage, link: CourseLink): Course {
        return Course(url = link.url,
                languageLevel = page.languageLevel?.text,
                classType = page.classType?.text,
                date = page.date?.text,
                classTopic = page.classTopic?.text,
                classTopicType = page.classTopicType?.text,
                learningOutcome = page.learningOutcome?.text)
    }

    data class CourseLink(val url: String, val topic: String)


}