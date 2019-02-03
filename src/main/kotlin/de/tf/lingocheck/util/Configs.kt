package de.tf.lingocheck.util

import java.io.FileInputStream
import java.util.*

object Configs {

    private var properties: Properties = loadProperties()

    private fun loadProperties(): Properties {
        return Properties().also { it.load(FileInputStream("config.properties")) }
    }

    fun getProperty(property: String): String {
        return properties.getProperty(property).toString()
    }
}