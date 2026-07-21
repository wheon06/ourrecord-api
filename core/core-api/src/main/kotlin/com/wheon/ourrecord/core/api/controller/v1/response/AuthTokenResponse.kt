package com.wheon.ourrecord.core.api.controller.v1.response

data class AuthTokenResponse(
    val accessToken: String,
    val refreshToken: String,
)
