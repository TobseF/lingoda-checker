package de.tf.lingocheck.util

import java.io.FileInputStream
import java.util.*

object Configs {

    private var properties: Properties = loadProperties()

    private fun loadProperties(): Properties {
        return Properties().also { it.load(FileInputStream("config.properties")) }
    }

    fun getProperty(propertyKey: String): String {
        return properties.getProperty(propertyKey)
                ?: throw IllegalStateException("Failed finding property `$propertyKey`")
    }

    val teacherUrl: String by lazy { getProperty("teacherUrl") }

    val username: String by lazy { getProperty("username") }
    val password: String by lazy { getProperty("password") }

    val pusherUrl: String by lazy { getProperty("pusherUrl") }
    val authorization: String by lazy { getProperty("authorization") }

    val pageURL: String by lazy { getProperty("pageURL") }
    val nameDriver: String by lazy { getProperty("nameDriver") }
    val exeDriver: String by lazy { getProperty("exeDriver") }
    val pathDriver: String by lazy { getProperty("pathDriver") }
    val commitUrl: String by lazy { getProperty("commitUrl") }
}