package de.tf.lingocheck.util

import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

fun createDriver(): ChromeDriver {
    System.setProperty(Configs.nameDriver, Configs.pathDriver + Configs.exeDriver)
    //.setBinary(getProperty("chromeDriverExe"))
    //ChromeOptions().setHeadless(true)
    val chromeDriver = ChromeDriver()
    @UseExperimental(ExperimentalTime::class) chromeDriver.setTimeOut(2200.milliseconds)
    return chromeDriver
}

@ExperimentalTime
fun ChromeDriver.setTimeOut(duration: Duration) {
    this.manage()?.timeouts()?.implicitlyWait(duration.inMilliseconds.toLong(), TimeUnit.MILLISECONDS)
}
