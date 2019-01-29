package de.tf.lingocheck.util

import org.openqa.selenium.WebDriver
import java.net.URI

abstract class TestBase {

    protected var driver: WebDriver

    init {
        driver = createDriver()
        driver.get(URI(UtilResources.getProperties("pageURL")).toString())
    }

    fun driverClose() {
        driver.close()
    }
}