package com.wheon.ourrecord.domain

import org.springframework.stereotype.Component
import java.security.SecureRandom

@Component
class CoupleInviteKeyGenerator {
    private val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    private val random = SecureRandom()

    fun generate(): String {
        return (1..8)
            .map { random.nextInt(charPool.length) }
            .map(charPool::get)
            .joinToString("")
    }
}
