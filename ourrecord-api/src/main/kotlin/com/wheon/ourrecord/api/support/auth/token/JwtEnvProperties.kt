package com.wheon.ourrecord.api.support.auth.token

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
data class JwtEnvProperties @ConstructorBinding constructor(
    val secretKey: String,
    val accessTokenExpireTime: Long,
    val refreshTokenExpireTime: Long,
)
