package com.wheon.ourrecord.core.support.auth

data class LoginResult(
    val userId: Long,
    val accessKey: String,
    val refreshKey: String,
)
