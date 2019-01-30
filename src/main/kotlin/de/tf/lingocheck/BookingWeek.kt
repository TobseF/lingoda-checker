package de.tf.lingocheck

import java.time.LocalDateTime

class BookingWeek(week: Week, val courses: List<LocalDateTime>) : Week(week.start) {

}