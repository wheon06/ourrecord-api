package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.core.enums.SocialProviderType

data class SocialProfile(
    val provider: SocialProviderType,
    val providerUserId: String,
    val name: String,
)
