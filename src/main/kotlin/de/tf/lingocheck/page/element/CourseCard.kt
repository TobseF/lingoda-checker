package de.tf.lingocheck.page.element

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.time.LocalDate
import java.time.LocalDateTime

/** A course calendar page which displays seven days, beginning with the #startDay
 * @param startDay first day of this course card.
 */
class CourseCard(webElement: WebElement, driver: WebDriver, startDay: LocalDate) : Component(webElement, driver) {

    val type: String  by lazy {
        webElement.findElement(By.xpath("a//h3"))?.text!!
    }

    val topic: String by lazy {
        webElement.findElement(By.xpath("a"))?.getAttribute("data-tooltip")!!
    }

    /**
     * Link which open the course commit dialog
     */
    private val action: WebElement by lazy {
        webElement.findElement(By.xpath("a"))!!
    }

    val classTime: String by lazy {
        webElement.findElement(By.xpath("a//strong[@class='class-time']"))?.text!!
    }

    val bookingLink: String? by lazy {
        webElement.findElement(By.xpath("a"))?.getAttribute("href")
    }

    val date: LocalDateTime by lazy {
        startDay.atTime(parseCourseTime(classTime), 0)
    }

    override fun toString(): String {
        return "Course($date: $type - $topic ($bookingLink)"
    }

    fun commit() {
        action.click()
        getCommitButton().click()
    }

    fun getCommitButton(): WebElement {
        return driver.findElement(By.xpath("//button[text()='Commit']"))
    }

    fun getCancelButton(): WebElement {
        return driver.findElement(By.xpath("a[@data-dismiss='modal' and text()='Cancel']"))
    }

}

/**
 * @param dateText German time stamp of course card. e.G. `2:00vorm.` or `2:00nachm.`
 * @return hour of the day
 */
fun parseCourseTime(dateText: String): Int {
    var time = dateText.substringBefore(":").toInt()
    if (dateText.contains("nachm.")) {
        time += 12
    }
    return time
}


