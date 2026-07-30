package com.wheon.ourrecord.core.domain

import com.wheon.ourrecord.storage.db.core.UserSessionEntity
import com.wheon.ourrecord.storage.db.core.UserSessionRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserSessionManager(
    private val userSessionRepository: UserSessionRepository,
) {
    companion object {
        private const val SESSION_EXPIRES_DAYS = 14L
    }

    fun create(userId: Long): Long {
        val now = LocalDateTime.now()
        val session = userSessionRepository.save(
            UserSessionEntity(
                userId = userId,
                userDeviceId = null,
                expiresAt = now.plusDays(SESSION_EXPIRES_DAYS),
                lastUsedAt = now,
            ),
        )
        return session.id
    }
}
