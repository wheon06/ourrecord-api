package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.SocialProviderType
import org.springframework.data.jpa.repository.JpaRepository

interface UserAuthIdentityRepository : JpaRepository<UserAuthIdentityEntity, Long> {
    fun findByProviderAndProviderUserId(provider: SocialProviderType, providerUserId: String): UserAuthIdentityEntity?
}
