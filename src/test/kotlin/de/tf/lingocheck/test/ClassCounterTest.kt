package de.tf.lingocheck.test

import de.tf.lingocheck.util.ClassCounter
import org.junit.Test
import kotlin.test.assertEquals

class ClassCounterTest {

    @Test
    fun testCounter() {
        val classCount = ClassCounter.getClassCount()
        ClassCounter.count()
        assertEquals(classCount + 1, ClassCounter.getClassCount())
    }

}