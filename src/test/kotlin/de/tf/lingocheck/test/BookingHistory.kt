package de.tf.lingocheck.test

import de.tf.lingocheck.test.mock.mockCourse
import de.tf.lingocheck.util.BookingHistoryApi
import org.junit.Ignore
import org.junit.Test

class BookingHistory {

    @Ignore
    @Test
    fun parseCourseTest() {
        BookingHistoryApi.bookCourse(mockCourse())
    }
}

