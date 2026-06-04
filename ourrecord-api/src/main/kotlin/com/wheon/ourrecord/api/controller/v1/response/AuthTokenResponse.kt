package com.wheon.ourrecord.api.controller.v1.response

data class AuthTokenResponse(
    val accessToken: String,
    val refreshToken: String,
)
