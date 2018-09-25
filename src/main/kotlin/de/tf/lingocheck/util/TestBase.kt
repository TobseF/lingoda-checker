package de.tf.lingocheck.util

import de.tf.lingocheck.util.UtilResources.getProperties
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.net.URI
import java.util.concurrent.TimeUnit

abstract class TestBase {

    protected var driver: WebDriver

    init {
        System.setProperty(getProperties("nameDriver"), getProperties("pathDriver") + getProperties("exeDriver"))

        //.setBinary(getProperties("chromeDriverExe"))
        //ChromeOptions().setHeadless(true)
        driver = ChromeDriver()
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        //driver.manage()?.window()?.maximize()
        driver.get(URI(UtilResources.getProperties("pageURL")).toString())

    }

    fun driverClose() {
        driver.close()
    }
}