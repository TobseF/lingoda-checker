package de.tf.lingocheck.test

import de.tf.lingocheck.page.ClassesPage
import de.tf.lingocheck.page.element.parseCourseTime
import de.tf.lingocheck.test.util.getResource
import de.tf.lingocheck.util.createDriver
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class ParsePageTest {

    @Test
    fun parseCourseTest() {
        val driver = createDriver()
        val coursedPage = getResource(this::class, "courses-page.html")
        driver.get(coursedPage.toString())
        val classesPage = ClassesPage(driver)
        val date = LocalDate.of(2019, 1, 28)
        val courses = classesPage.findCourses(date)
        val firstCourse = courses.first()
        assertEquals("2:00vorm.", firstCourse.classTime)
        assertEquals("Group class", firstCourse.type)
        assertEquals("Topic: Immer wieder 'hätte'", firstCourse.topic)
        assertEquals("/teacher/classes/commit/895338", firstCourse.bookingLink.webLink())

        val coursesDay2 = classesPage.findCoursesOnDay(2, LocalDate.of(2019, 1, 29))
        assertEquals(coursesDay2.size, 2)
        val coursesDay3 = classesPage.findCoursesOnDay(3, LocalDate.of(2019, 1, 30))
        assertEquals(coursesDay3.size, 0)
        val coursesDay4 = classesPage.findCoursesOnDay(4, LocalDate.of(2019, 1, 31))
        assertEquals(coursesDay4.size, 1)


        val allCourses = classesPage.findCoursesByDays(date)
        allCourses.first()
    }

    @Test
    fun pageUrlTest() {
        val date = ClassesPage.PageUrl.getDate("teacher.lingoda.com/teacher/classes/2019-01-28")
        assertEquals(LocalDate.of(2019, 1, 28), date)
        val date2 = ClassesPage.PageUrl.getDate("teacher.lingoda.com/teacher/classes/2019-01-28?from=prev")
        assertEquals(LocalDate.of(2019, 1, 28), date2)
    }

    @Test
    fun courseTimeStampTest() {
        assertEquals(2, parseCourseTime("2:00vorm."))
        assertEquals(14, parseCourseTime("2:00nachm."))
        assertEquals(12, parseCourseTime("12:00nachm."))
    }

    fun String?.webLink() = this?.substringAfter("file:///C:")
}

