package de.tf.lingocheck.by.api.url

import de.tf.lingocheck.util.Configs
import de.tf.lingocheck.util.createDriver
import org.openqa.selenium.WebDriver

abstract class TestBase {

    protected var driver: WebDriver = createDriver()

    init {
        driver.get(Configs.pageURL)
    }


}