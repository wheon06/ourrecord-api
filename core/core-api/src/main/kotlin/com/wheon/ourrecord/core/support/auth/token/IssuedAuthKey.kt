package com.wheon.ourrecord.core.support.auth.token

data class IssuedAuthKey(
    val accessKey: String,
    val refreshKey: String,
)
