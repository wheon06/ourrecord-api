package com.wheon.ourrecord.domain

import com.wheon.ourrecord.api.support.auth.token.RefreshTokenHasher
import com.wheon.ourrecord.storage.db.core.UserSessionEntity
import com.wheon.ourrecord.storage.db.core.UserSessionRepository
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
