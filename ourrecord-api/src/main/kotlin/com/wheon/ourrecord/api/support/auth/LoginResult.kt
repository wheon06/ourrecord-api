package com.wheon.ourrecord.api.support.auth

data class LoginResult(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String,
)
