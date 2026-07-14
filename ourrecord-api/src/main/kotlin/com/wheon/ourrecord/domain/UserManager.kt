package com.wheon.ourrecord.domain

import com.wheon.ourrecord.storage.db.core.UserIdentityEntity
import com.wheon.ourrecord.storage.db.core.UserIdentityRepository
import com.wheon.ourrecord.storage.db.core.UserEntity
import com.wheon.ourrecord.storage.db.core.UserRepository
import com.wheon.ourrecord.support.auth.SocialProfile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class UserManager(
    private val userRepository: UserRepository,
    private val userAuthIdentityRepository: UserIdentityRepository,
) {
    @Transactional
    fun createSocialUser(profile: SocialProfile): Long {
        val savedUser = userRepository.save(
            UserEntity(
                state = UserState.ENABLED,
                nickname = profile.name,
            ),
        )

        userAuthIdentityRepository.save(
            UserIdentityEntity(
                userId = savedUser.id,
                provider = profile.provider,
                providerUserId = profile.providerUserId,
                linkedAt = LocalDateTime.now(),
            ),
        )

        return savedUser.id
    }
}
