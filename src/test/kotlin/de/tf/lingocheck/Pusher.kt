package de.tf.lingocheck

import com.github.kittinunf.fuel.Fuel
import de.tf.lingocheck.util.UtilResources

class Pusher {

    private val authorization: String
    private val url: String

    init {
        authorization = UtilResources.getProperties("authorization")
        url = UtilResources.getProperties("pusherUrl")
    }

    fun send(title: String, body: String): Int {
        return Fuel.post(url).body(jsonData(title, body)).header(mapOf("Content-Type" to "application/json"))
                .header(mapOf("Authorization" to "Bearer $authorization")).response().second.statusCode
    }

    private fun jsonData(title: String, body: String): String {
        return """
            {
              "interests": [
                "hello"
              ],
              "fcm": {
                "notification": {
                  "title": "$title",
                  "body": "$body"
                }
              }
            }
        """.trimIndent()
    }
}