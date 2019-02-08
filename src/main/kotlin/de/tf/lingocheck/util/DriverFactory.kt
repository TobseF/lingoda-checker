package de.tf.lingocheck.util

import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

fun createDriver(): ChromeDriver {
    System.setProperty(Configs.nameDriver, Configs.pathDriver + Configs.exeDriver)
    //.setBinary(getProperty("chromeDriverExe"))
    //ChromeOptions().setHeadless(true)
    val chromeDriver = ChromeDriver()
    chromeDriver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
    return chromeDriver
}