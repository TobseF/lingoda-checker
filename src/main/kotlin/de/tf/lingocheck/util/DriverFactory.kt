package de.tf.lingocheck.util

import mu.KotlinLogging
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

fun createDriver(): ChromeDriver {
    System.setProperty(Configs.nameDriver, Configs.pathDriver + Configs.exeDriver)
    val chromeDriver = ChromeDriver()
    chromeDriver.setTimeOut(Configs.timeOutDefault)
    return chromeDriver
}

fun WebDriver.setTimeOut(milliseconds: Long) {
    this.manage()?.timeouts()?.implicitlyWait(milliseconds, TimeUnit.MILLISECONDS)
}

fun WebDriver.retryWithLongTimeout(refreshOnError: Boolean = false, runnable: () -> Any) {
    this.retryWithLongTimeoutOnError(runnable, null, refreshOnError)
}

private val log = KotlinLogging.logger("DriverFactory")

fun WebDriver.retryWithLongTimeoutOnError(runnable: () -> Any,
        runOnError: (() -> Any)? = null,
        refreshOnError: Boolean = false) {
    this.setTimeOut(Configs.timeOutLong)
    val retriesMax = 4
    var retries = retriesMax

    fun handleException(e: RuntimeException) {
        if (retries == 0) {
            if (runOnError == null) {
                log.error("Failed to execute WebDriver action ($retries/$retriesMax)")
                throw e
            } else {
                runOnError.invoke()
            }
        } else {
            if (refreshOnError) {
                this.navigate().refresh()
            }
            retries--
        }
    }

    while (retries >= 0) {
        try {
            runnable.invoke()
            retries = 0
        } catch (e: RuntimeException) {
            handleException(e)
        }
    }
    this.setTimeOut(Configs.timeOutDefault)
}