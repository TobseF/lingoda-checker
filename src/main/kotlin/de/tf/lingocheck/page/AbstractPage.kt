package de.tf.lingocheck.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

abstract class AbstractPage(protected val driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    fun findBy(xPath: String): WebElement {
        return driver.findElement(By.xpath(xPath))
    }

    fun lazyFindAll(xPath: String): Lazy<MutableList<WebElement>> {
        return lazy { findAll(xPath) }
    }

    fun findAll(xPath: String): MutableList<WebElement> = driver.findElements(By.xpath(xPath))

}