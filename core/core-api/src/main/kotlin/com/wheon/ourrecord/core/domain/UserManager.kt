package com.wheon.ourrecord.core.domain

import com.wheon.ourrecord.storage.db.core.UserIdentityRepository
import com.wheon.ourrecord.storage.db.core.UserRepository
import org.springframework.stereotype.Component

@Component
class UserManager(
    private val userRepository: UserRepository,
    private val userAuthIdentityRepository: UserIdentityRepository,
)
