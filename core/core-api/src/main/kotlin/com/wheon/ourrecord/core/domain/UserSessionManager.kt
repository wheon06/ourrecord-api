package com.wheon.ourrecord.core.domain

import com.wheon.ourrecord.storage.db.core.UserSessionRepository
import org.springframework.stereotype.Component

@Component
class UserSessionManager(
    private val userSessionRepository: UserSessionRepository,
)
