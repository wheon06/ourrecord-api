package com.wheon.ourrecord.auth.api.controller.v1.response

data class KakaoLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val coupleId: String,
)
