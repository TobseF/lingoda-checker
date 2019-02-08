package de.tf.lingocheck.test

import de.tf.lingocheck.api.CsvMaperApiCourse
import de.tf.lingocheck.api.CsvMaperApiCourse.parseCsv
import de.tf.lingocheck.test.mock.mockCourse
import org.junit.Test
import kotlin.test.assertEquals

class ParseApiCourseTest {

    @Test
    fun parseCourseTest() {
        //779390;21.11.2018 15:00;Private class;Speaking;A2.2;A2_2038S_DE;A conversation about shopping and spending money
        assertEquals(mockCourse(), parseCsv(CsvMaperApiCourse.toCsv(mockCourse())))
    }
}

