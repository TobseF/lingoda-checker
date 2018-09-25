package de.tf.lingocheck.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

abstract class AbstractPage(driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

}