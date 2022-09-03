package dev.juanrincon.data.utilities

import io.ktor.server.application.*
import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object AuthUtilities {
    fun hash(password: String, jwtSecret: String): String {
        val hashKey = hex(jwtSecret)
        val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")
        val hmac = Mac.getInstance("HmacSHA1")
        hmac.init(hmacKey)
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }
}