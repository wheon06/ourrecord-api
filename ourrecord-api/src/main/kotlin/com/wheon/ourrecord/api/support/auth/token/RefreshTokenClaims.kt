package com.wheon.ourrecord.api.support.auth.token

data class RefreshTokenClaims(
    val userId: Long,
    val refreshTokenId: String,
)
