package com.wheon.ourrecord.support.auth.token

data class RefreshTokenClaims(
    val userId: Long,
    val refreshTokenId: String,
)
