package de.tf.lingocheck.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class ClassCommitPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = "//td[text()='Language Level']/../td[2]")
    val languageLevel: WebElement? = null

    @FindBy(xpath = "//td[text()='Topic of class']/../td[2]")
    val classTopic: WebElement? = null

    @FindBy(xpath = "//td[text()='Topic type']/../td[2]")
    val classTopicType: WebElement? = null

    @FindBy(xpath = "//td[text()='Class type']/../td[2]")
    val classType: WebElement? = null

    @FindBy(xpath = "//td[text()='Learning Outcome']/../td[2]")
    val learningOutcome: WebElement? = null

    @FindBy(xpath = "//thead/tr")
    val date: WebElement? = null


}