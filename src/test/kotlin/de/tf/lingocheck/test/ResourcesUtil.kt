package de.tf.lingocheck.test

import java.net.URL
import kotlin.reflect.KClass


fun getResource(clazz: KClass<out Any>, fileName: String): URL {
    val filePath = clazz.java.`package`.name + "/" + fileName
    try {
        return clazz.java.classLoader.getResource(filePath)

    } catch (e: IllegalStateException) {
        throw IllegalStateException("Failed finding testfile: $filePath")
    }
}
