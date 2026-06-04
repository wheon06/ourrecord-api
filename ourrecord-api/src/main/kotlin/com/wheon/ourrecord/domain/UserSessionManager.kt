package com.wheon.ourrecord.domain

import com.wheon.ourrecord.storage.db.core.UserSessionEntity
import com.wheon.ourrecord.storage.db.core.UserSessionRepository
import com.wheon.ourrecord.support.auth.token.RefreshTokenHasher
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserSessionManager(
    private val userSessionRepository: UserSessionRepository,
    private val refreshTokenHasher: RefreshTokenHasher,
) {
    fun create(userId: Long, userDeviceId: Long, refreshToken: String, refreshTokenId: String, expiresAt: LocalDateTime) {
        userSessionRepository.save(
            UserSessionEntity(
                userId = userId,
                userDeviceId = userDeviceId,
                refreshTokenId = refreshTokenId,
                refreshTokenHash = refreshTokenHasher.hash(refreshToken),
                expiresAt = expiresAt,
                lastUsedAt = LocalDateTime.now(),
                revokedAt = null,
            ),
        )
    }
}
