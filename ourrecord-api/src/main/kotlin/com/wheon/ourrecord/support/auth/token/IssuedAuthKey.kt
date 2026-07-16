package com.wheon.ourrecord.support.auth.token

data class IssuedAuthKey(
    val accessKey: String,
    val refreshKey: String,
)
