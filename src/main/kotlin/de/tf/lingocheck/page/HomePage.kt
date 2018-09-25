package de.tf.lingocheck.page

import de.tf.lingocheck.util.UtilResources.getProperties
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(val driver: WebDriver) : AbstractPage(driver) {

    @FindBy(xpath = ".//input[@type='email']")
    private val inputName: WebElement? = null

    @FindBy(xpath = ".//input[@type='password']")
    private val inputPassword: WebElement? = null

    @FindBy(xpath = ".//button[@type='submit']")
    private val buttonLogin: WebElement? = null

    @FindBy(xpath = ".//input[@type='checkbox']")
    private val checkBoxKeepLogin: WebElement? = null


    fun login(): ClassesPage {
        inputName?.sendKeys(getProperties("username"))
        inputPassword?.sendKeys(getProperties("password"))
        checkBoxKeepLogin?.click()
        buttonLogin?.click()
        return ClassesPage(driver)
    }
}