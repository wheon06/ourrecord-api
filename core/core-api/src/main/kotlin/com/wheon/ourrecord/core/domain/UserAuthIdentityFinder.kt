package com.wheon.ourrecord.core.domain

import com.wheon.ourrecord.core.enums.IdentityProviderType
import com.wheon.ourrecord.storage.db.core.UserIdentityRepository
import org.springframework.stereotype.Component

@Component
class UserAuthIdentityFinder(
    private val userAuthIdentityRepository: UserIdentityRepository,
) {
    fun findUserIdOrNull(provider: IdentityProviderType, providerUserId: String): Long? {
        val userAuthIdentity = userAuthIdentityRepository.findByProviderAndProviderSubject(
            provider = provider,
            providerSubject = providerUserId,
        )

        return userAuthIdentity?.userId
    }
}
