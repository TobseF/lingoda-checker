package de.tf.lingocheck.page.element

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.time.LocalDate
import java.time.LocalDateTime

/** A course calendar page which displays seven days, beginning with the #startDay
 * @param startDay first day of this course card.
 */
class CourseCard(webElement: WebElement, startDay: LocalDate) {

    val type: String  by lazy {
        webElement.findElement(By.xpath("a//h3"))?.text!!
    }

    val topic: String by lazy {
        webElement.findElement(By.xpath("a"))?.getAttribute("data-tooltip")!!
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


