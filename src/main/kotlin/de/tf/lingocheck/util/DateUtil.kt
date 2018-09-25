package de.tf.lingocheck.util

import java.time.Duration
import java.util.*

fun Date.isLaterThan(hours: Int, now: Date = Date()): Boolean {
    val millis = Duration.ofHours(hours.toLong()).toMillis()
    return this.time > (now.time + millis)
}
