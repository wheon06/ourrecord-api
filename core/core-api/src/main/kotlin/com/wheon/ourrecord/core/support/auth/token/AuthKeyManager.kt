package com.wheon.ourrecord.core.support.auth.token

import com.wheon.ourrecord.core.enums.AuthKeyState
import com.wheon.ourrecord.core.enums.AuthKeyType
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.AuthKeyEntity
import com.wheon.ourrecord.storage.db.core.AuthKeyRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Component
class AuthKeyManager(
    private val authKeyRepository: AuthKeyRepository,
) {
    companion object {
        private const val ACCESS_KEY_EXPIRES_DAYS = 7L
        private const val REFRESH_KEY_EXPIRES_DAYS = 14L
    }

    fun issue(userId: Long, sessionId: Long, keyType: AuthKeyType): String {
        val now = LocalDateTime.now()
        val authKey = authKeyRepository.save(
            AuthKeyEntity(
                userId = userId,
                sessionId = sessionId,
                type = keyType,
                key = "${now.year}-${now.monthValue}-${now.dayOfMonth}_${keyType}_${UUID.randomUUID()}",
                issuedAt = now,
                expiresAt = when (keyType) {
                    AuthKeyType.ACCESS -> now.plusDays(ACCESS_KEY_EXPIRES_DAYS)
                    AuthKeyType.REFRESH -> now.plusDays(REFRESH_KEY_EXPIRES_DAYS)
                },
            ),
        )

        return authKey.key
    }

    @Transactional
    fun reissue(userId: Long, sessionId: Long, refreshKey: String): IssuedAuthKey {
        val existingRefreshKey = authKeyRepository.findByUserIdAndSessionIdAndKeyAndType(
            userId = userId,
            sessionId = sessionId,
            key = refreshKey,
            type = AuthKeyType.REFRESH,
        ) ?: throw CoreException(ErrorType.REFRESH_KEY_INVALID)
        if (!existingRefreshKey.isActive()) throw CoreException(ErrorType.REFRESH_KEY_INVALID)

        authKeyRepository.findByUserIdAndSessionIdAndTypeAndState(
            userId = userId,
            sessionId = sessionId,
            type = AuthKeyType.ACCESS,
            state = AuthKeyState.ACTIVE,
        ).forEach { it.revoke() }

        existingRefreshKey.revoke()

        return IssuedAuthKey(
            accessKey = issue(userId, sessionId, AuthKeyType.ACCESS),
            refreshKey = issue(userId, sessionId, AuthKeyType.REFRESH),
        )
    }

    fun verify(authKey: String): Long {
        val accessKey = authKeyRepository.findByKeyAndType(authKey, AuthKeyType.ACCESS)
            ?: throw CoreException(ErrorType.AUTHENTICATED_SESSION_EXPIRED)
        if (!accessKey.isActive()) throw CoreException(ErrorType.AUTHENTICATED_SESSION_EXPIRED)
        return accessKey.userId
    }
}
