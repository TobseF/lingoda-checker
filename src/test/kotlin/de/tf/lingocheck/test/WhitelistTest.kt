package de.tf.lingocheck.test

import de.tf.lingocheck.Whitelist
import de.tf.lingocheck.test.util.dateSet
import org.junit.Test
import kotlin.test.assertEquals

class WhitelistTest {

    @Test
    fun whitelistTest() {
        Whitelist.read(listOf("01.12.2018 08:00", "01.12.2018 13:00 - 19:00"))
        println(Whitelist.whitelist)
        assertEquals(dateSet("01.12.2018 08:00",
                "01.12.2018 13:00",
                "01.12.2018 14:00",
                "01.12.2018 15:00",
                "01.12.2018 16:00",
                "01.12.2018 17:00",
                "01.12.2018 18:00",
                "01.12.2018 19:00"), Whitelist.whitelist)
    }


    @Test
    fun whitelistCommentTest() {
        Whitelist.read(listOf("01.12.2018 08:00", "#Somme Comment", "01.12.2018 13:00 - 19:00"))
        println(Whitelist.whitelist)
        assertEquals(dateSet("01.12.2018 08:00",
                "01.12.2018 13:00",
                "01.12.2018 14:00",
                "01.12.2018 15:00",
                "01.12.2018 16:00",
                "01.12.2018 17:00",
                "01.12.2018 18:00",
                "01.12.2018 19:00"), Whitelist.whitelist)
    }
}