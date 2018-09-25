package de.tf.lingocheck.util

import java.io.File
import java.io.FileWriter
import java.nio.charset.Charset.defaultCharset

object ClassCounter {
    private const val counterFile = "class_count.txt"
    private var count = 0L

    private fun read() {
        count = File(counterFile).readText(defaultCharset()).toLong()
    }

    fun getClassCount(): Long {
        if (count == 0L) {
            read()
        }
        return count
    }

    fun count() {
        count++
        write()
    }

    private fun write() {
        val fileWriter = FileWriter(File(counterFile))
        fileWriter.write(count.toString())
        fileWriter.close()
    }


}