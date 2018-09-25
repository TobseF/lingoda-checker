package de.tf.lingocheck.test

import de.tf.lingocheck.mock.mockCourse
import de.tf.lingocheck.util.BookingHistory
import org.testng.annotations.Test

class BookingHistroy {

    @Test
    fun parseCourseTest() {

        BookingHistory.bookCourse(mockCourse())
    }
}

