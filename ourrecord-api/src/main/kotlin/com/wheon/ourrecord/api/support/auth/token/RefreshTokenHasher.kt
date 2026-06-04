package com.wheon.ourrecord.api.support.auth.token

import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.util.HexFormat

@Component
class RefreshTokenHasher {
    fun hash(refreshToken: String): String {
        val digest = MessageDigest
            .getInstance("SHA-256")
            .digest(refreshToken.toByteArray())

        return HexFormat.of().formatHex(digest)
    }
}
