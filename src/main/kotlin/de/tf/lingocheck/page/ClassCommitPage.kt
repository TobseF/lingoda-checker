package de.tf.lingocheck.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

typealias elementList = MutableList<WebElement>

class ClassCommitPage(driver: WebDriver) : AbstractPage(driver) {


    val languageLevel: elementList by lazyFindAll("//td[text()='Language Level']/../td[2]")


    val classTopic: elementList by lazyFindAll("//td[text()='Topic of class']/../td[2]")


    val classTopicType: elementList by lazyFindAll("//td[text()='Topic type']/../td[2]")


    val classType: elementList by lazyFindAll("//td[text()='Class type']/../td[2]")


    val learningOutcome: elementList by lazyFindAll("//td[text()='Learning Outcome']/../td[2]")


    val presentationNumber: elementList by lazyFindAll("//td[text()='Presentation Number']/../td[2]")


    val date: elementList by lazyFindAll("//thead/tr")


    val statusTakenClass: elementList by lazyFindAll("//body/div/h3[text()='Another teacher already committed this class']")


    val statusMissing: elementList by lazyFindAll("//body[text()='Linguando\\MainBundle\\Entity\\Event object not found by the @ParamConverter annotation.']")


    val buttonCommit: elementList by lazyFindAll(".//button[@type='submit']")


    fun isCommitPage() = date.isNotEmpty() && classTopic.isNotEmpty()

    fun isMissingPage() = statusMissing.isNotEmpty()

    fun isTakenPage() = statusTakenClass.isNotEmpty()

    fun commit() {
        buttonCommit.first().click()
    }
}