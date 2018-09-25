package de.tf.lingocheck.test

import de.tf.lingocheck.mock.mockCourse
import de.tf.lingocheck.parseCsv
import de.tf.lingocheck.toCsv
import org.testng.annotations.Test
import kotlin.test.assertEquals

class ParseCourseTest {

    @Test
    fun parseCourseTest() {

        assertEquals(mockCourse(), parseCsv(mockCourse().toCsv()))
        "779390;21.11.2018 15:00;Private class;Speaking;A2.2;A2_2038S_DE;A conversation about shopping and spending money"
    }
}

