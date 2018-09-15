package de.tf.lingocheck.util

import de.tf.lingocheck.util.UtilResources.getProperties
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import java.net.URI
import java.util.concurrent.TimeUnit

abstract class TestBase {

    protected lateinit var driver: WebDriver

    @BeforeTest
    fun setup() {
        System.setProperty(getProperties("nameDriver"), getProperties("pathDriver") + getProperties("exeDriver"))

        //.setBinary(getProperties("chromeDriverExe"))
        //ChromeOptions().setHeadless(true)
        driver = ChromeDriver()
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        //driver.manage()?.window()?.maximize()
        driver.get(URI(UtilResources.getProperties("pageURL")).toString())
    }

    @AfterTest
    fun driverClose() {
        driver.close()
    }
}