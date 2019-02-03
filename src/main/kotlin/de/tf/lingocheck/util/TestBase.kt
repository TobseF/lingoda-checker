package de.tf.lingocheck.util

import org.openqa.selenium.WebDriver
import java.net.URI

abstract class TestBase {

    protected var driver: WebDriver = createDriver()

    init {
        driver.get(URI(Configs.getProperty("pageURL")).toString())
    }

    fun closeDriver() {
        driver.close()
    }
}