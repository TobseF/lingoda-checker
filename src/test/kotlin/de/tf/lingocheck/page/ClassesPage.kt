package de.tf.lingocheck.page

import de.tf.lingocheck.SearchCourses
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class ClassesPage(private val driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = "//a[@title=\"Next week\"]")
    val buttonNextWeek: WebElement? = null

    fun findCoursesLinks(): List<SearchCourses.CourseLink> {
        val freeCardElements = driver.findElements(By.ByXPath.xpath("//a[starts-with(@href, \"/teacher/classes/commit/\")]"))

        return freeCardElements.map { parseCard(it) }.toList()
    }

    fun parseCard(card: WebElement): SearchCourses.CourseLink {
        return SearchCourses.CourseLink(url = card.getAttribute("href"), topic = card.getAttribute("data-tooltip"))
    }
}