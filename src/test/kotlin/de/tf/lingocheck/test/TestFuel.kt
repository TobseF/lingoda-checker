package de.tf.lingocheck.test

import de.tf.lingocheck.Pusher
import org.testng.annotations.Test

class TestFuel {

    @Test
    fun tesFuel() {
        println(Pusher().send("Hi Java", "mystery"))
    }

}