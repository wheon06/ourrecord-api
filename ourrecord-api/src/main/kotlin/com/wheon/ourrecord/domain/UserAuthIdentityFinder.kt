package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.SocialProviderType
import com.wheon.ourrecord.storage.db.core.UserAuthIdentityRepository
import org.springframework.stereotype.Component

@Component
class UserAuthIdentityFinder(
    private val userAuthIdentityRepository: UserAuthIdentityRepository,
) {
    fun findUserIdOrNull(provider: SocialProviderType, providerUserId: String): Long? {
        val userAuthIdentity = userAuthIdentityRepository.findByProviderAndProviderUserId(
            provider = provider,
            providerUserId = providerUserId,
        )

        return userAuthIdentity?.userId
    }
}
