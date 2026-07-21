package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.core.enums.IdentityProviderType

data class SocialProfile(
    val provider: IdentityProviderType,
    val providerUserId: String,
    val name: String,
)
