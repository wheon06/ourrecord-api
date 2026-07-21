package com.wheon.ourrecord.support.auth

data class LoginResult(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String,
)
