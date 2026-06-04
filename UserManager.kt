package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.UserState
import com.wheon.ourrecord.storage.db.core.UserAuthIdentityEntity
import com.wheon.ourrecord.storage.db.core.UserAuthIdentityRepository
import com.wheon.ourrecord.storage.db.core.UserEntity
import com.wheon.ourrecord.storage.db.core.UserRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserManager(
    private val userRepository: UserRepository,
    private val userAuthIdentityRepository: UserAuthIdentityRepository,
) {
    fun createSocialUser(profile: SocialProfile): Long {
        val savedUser = userRepository.save(
            UserEntity(
                state = UserState.ENABLED,
                nickname = profile.name
            )
        )

        userAuthIdentityRepository.save(
            UserAuthIdentityEntity(
                userId = savedUser.id,
                provider = profile.provider,
                providerUserId = profile.providerUserId,
                providerProfileJson = "{}",
                linkedAt = LocalDateTime.now(),
            )
        )
    }
}
