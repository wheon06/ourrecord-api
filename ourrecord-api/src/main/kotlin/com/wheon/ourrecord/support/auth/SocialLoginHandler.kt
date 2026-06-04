package com.wheon.ourrecord.support.auth

import com.wheon.ourrecord.domain.UserAuthIdentityFinder
import com.wheon.ourrecord.domain.UserManager
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

        return userManager.createSocialUser(profile)
    }
}
