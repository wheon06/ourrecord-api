package com.wheon.ourrecord.core.support.auth

import com.wheon.ourrecord.storage.db.core.UserEntity
import com.wheon.ourrecord.storage.db.core.UserIdentityEntity
import com.wheon.ourrecord.storage.db.core.UserIdentityRepository
import com.wheon.ourrecord.storage.db.core.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class UserProvisioner(
    private val userRepository: UserRepository,
    private val userAuthIdentityRepository: UserIdentityRepository,
) {
    @Transactional
    fun getOrProvision(profile: SocialProfile): ProvisionedUser {
        val existing = userAuthIdentityRepository.findByProviderTypeAndProviderSubject(
            providerType = profile.providerType,
            providerSubject = profile.providerUserId,
        )

        if (existing != null) {
            return ProvisionedUser(
                userId = existing.userId,
                isNewUser = false,
            )
        }

        val savedUser = userRepository.save(UserEntity())

        userAuthIdentityRepository.save(
            UserIdentityEntity(
                userId = savedUser.id,
                providerType = profile.providerType,
                providerSubject = profile.providerUserId,
                linkedAt = LocalDateTime.now(),
            ),
        )

        return ProvisionedUser(
            userId = savedUser.id,
            isNewUser = true,
        )
    }
}
