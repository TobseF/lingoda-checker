package de.tf.lingocheck.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

typealias elementList = MutableList<WebElement>

class ClassCommitPage(val driver: WebDriver) : AbstractPage(driver) {


    val languageLevel: elementList by lazy { findByXPath("//td[text()='Language Level']/../td[2]") }


    fun findByXPath(xPath: String) = driver.findElements(By.xpath(xPath))


    val classTopic: elementList by lazy { findByXPath("//td[text()='Topic of class']/../td[2]") }


    val classTopicType: elementList by lazy { findByXPath("//td[text()='Topic type']/../td[2]") }


    val classType: elementList by lazy { findByXPath("//td[text()='Class type']/../td[2]") }


    val learningOutcome: elementList by lazy { findByXPath("//td[text()='Learning Outcome']/../td[2]") }


    val presentationNumber: elementList by lazy { findByXPath("//td[text()='Presentation Number']/../td[2]") }


    val date: elementList by lazy { findByXPath("//thead/tr") }


    val statusTakenClass: elementList by lazy { findByXPath("//body/div/h3[text()='Another teacher already committed this class']") }


    val statusMissing: elementList by lazy { findByXPath("//body[text()='Linguando\\MainBundle\\Entity\\Event object not found by the @ParamConverter annotation.']") }


    val buttonCommit: elementList by lazy { findByXPath(".//button[@type='submit']") }


    fun isCommitPage() = date.isNotEmpty() && classTopic.isNotEmpty()

    fun isMissingPage() = statusMissing.isNotEmpty()

    fun isTakenPage() = statusTakenClass.isNotEmpty()

    fun commit() {
        buttonCommit.first().click()
    }
}