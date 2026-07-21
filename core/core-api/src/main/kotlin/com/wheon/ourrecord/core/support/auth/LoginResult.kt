package com.wheon.ourrecord.core.support.auth

data class LoginResult(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String,
)
