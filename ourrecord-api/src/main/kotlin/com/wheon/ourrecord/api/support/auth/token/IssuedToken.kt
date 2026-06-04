package com.wheon.ourrecord.api.support.auth.token

import java.time.LocalDateTime

data class IssuedToken(
    val accessToken: String,
    val refreshToken: String,
    val refreshTokenId: String,
    val accessTokenExpiresAt: LocalDateTime,
    val refreshTokenExpiresAt: LocalDateTime,
)
