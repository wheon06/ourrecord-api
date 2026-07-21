package com.wheon.ourrecord.core.api.controller.v1.response

data class AuthKeyResponse(
    val accessKey: String,
    val refreshKey: String,
)
