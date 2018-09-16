package de.tf.lingocheck.util

import java.io.FileInputStream
import java.util.*

object UtilResources {

    private var properties: Properties? = null

    private fun loadProperties() {
        properties = Properties()
        properties?.load(FileInputStream("config.properties"))
    }

    fun getProperties(property: String): String {
        if (properties == null) {
            loadProperties()
        }
        return UtilResources.properties?.getProperty(property).toString()
    }
}