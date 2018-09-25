package de.tf.lingocheck.test

import de.tf.lingocheck.test.mock.mockCourse
import de.tf.lingocheck.util.BookingHistory
import org.junit.Ignore
import org.junit.Test

class BookingHistroy {

    @Ignore
    @Test
    fun parseCourseTest() {

        BookingHistory.bookCourse(mockCourse())
    }
}

