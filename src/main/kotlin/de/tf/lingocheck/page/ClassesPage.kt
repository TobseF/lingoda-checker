package de.tf.lingocheck.page

import de.tf.lingocheck.SearchCourses
import de.tf.lingocheck.page.element.CourseCard
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import java.time.LocalDate


class ClassesPage(private val driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = "//a[@title=\"Next week\"]")
    val buttonNextWeek: WebElement? = null

    fun findCoursesLinks(): List<SearchCourses.CourseLink> {
        val freeCardElements = driver.findElements(By.ByXPath.xpath("//a[starts-with(@href, \"/teacher/classes/commit/\")]"))

        return freeCardElements.map { parseCard(it) }.toList()
    }

    fun findCourses(date: LocalDate): List<CourseCard> {
        return driver.findElements(groupClassOrPrivateClass()).map { CourseCard(it, date) }
    }

    fun findCoursesByDays(date: LocalDate): List<CourseCard> {
        val courses = arrayListOf<CourseCard>()
        for (day in 0..8) {
            val dayDate = date.plusDays(day.toLong())
            courses.addAll(findCoursesOnDay(day + 1, dayDate))
        }
        return courses
    }

    fun findCoursesOnDay(day: Int, date: LocalDate): List<CourseCard> {
        return driver.findElements(By.ByXPath(getGroupClassOrPrivateClassOnDay(day))).map { CourseCard(it, date) }
    }

    private fun groupClassOrPrivateClass() = By.ByXPath("//section[@class='calendar available-classes']/${getGroupClassOrPrivateClass()}")

    private fun getGroupClassOrPrivateClass() = "/div[contains(@class, 'calendar-class-private') or contains(@class, 'calendar-class-group')]"

    private fun getGroupClassOrPrivateClassOnDay(day: Int) = "${getActiveDaySection(day)}/${getGroupClassOrPrivateClass()}"

    private fun getActiveDaySection(day: Int) = """//${getAvailableClassesSection()}//div[@class="calendar-day" and contains(@ng-class, 'activeDay') and contains(@ng-class, '$day')]"""

    private fun getAvailableClassesSection() = "section[@class='calendar available-classes']"

    fun parseCard(card: WebElement): SearchCourses.CourseLink {
        return SearchCourses.CourseLink(url = card.getAttribute("href"), topic = card.getAttribute("data-tooltip"))
    }

    fun getDateFromUrl() = PageUrl(driver.currentUrl).getDate()

    class PageUrl(val url: String) {

        fun getDate(): LocalDate {

            val datePart = url.substringAfter("/classes/").substringBefore("?")
            val dateValues = datePart.split("-").map { it.toInt() }
            val date = LocalDate.of(dateValues[0], dateValues[1], dateValues[2])
            return date
        }
    }
}