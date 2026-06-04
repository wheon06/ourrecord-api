package com.wheon.ourrecord.api.controller.v1.response

data class LoginResponse(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String,

)
