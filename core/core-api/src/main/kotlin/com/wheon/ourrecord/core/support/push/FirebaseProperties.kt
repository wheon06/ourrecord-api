package com.wheon.ourrecord.support.push

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "firebase")
data class FirebaseProperties @ConstructorBinding constructor(
    val enabled: Boolean = false,
    val serviceAccountBase64: String,
    val projectId: String,
)
