package com.wheon.ourrecord.core.support.auth

import com.wheon.ourrecord.core.domain.UserAuthIdentityFinder
import com.wheon.ourrecord.core.domain.UserManager
import org.springframework.stereotype.Component

@Component
class SocialLoginHandler(
    private val userAuthIdentityFinder: UserAuthIdentityFinder,
    private val userManager: UserManager,
) {
    fun loginOrSignup(profile: SocialProfile): Long {
        val existingUserId = userAuthIdentityFinder.findUserIdOrNull(
            provider = profile.provider,
            providerUserId = profile.providerUserId,
        )

        if (existingUserId != null) {
            return existingUserId
        }

        return 1L
    }
}
