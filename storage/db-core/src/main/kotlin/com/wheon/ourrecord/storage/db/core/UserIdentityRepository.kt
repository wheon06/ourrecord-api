package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.IdentityProviderType
import org.springframework.data.jpa.repository.JpaRepository

interface UserIdentityRepository : JpaRepository<UserIdentityEntity, Long> {
    fun findByProviderTypeAndProviderSubject(providerType: IdentityProviderType, providerSubject: String): UserIdentityEntity?
}
