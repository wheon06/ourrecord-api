package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_session")
class UserSessionEntity(
    val userId: Long,
    val userDeviceId: Long? = null,
    val expiresAt: LocalDateTime,
    lastUsedAt: LocalDateTime,
    revokedAt: LocalDateTime? = null,
) : BaseIdEntity() {
    var lastUsedAt: LocalDateTime = lastUsedAt
        protected set

    var revokedAt: LocalDateTime? = revokedAt
        protected set

    fun use(now: LocalDateTime) {
        lastUsedAt = now
    }

    fun revoke(now: LocalDateTime) {
        revokedAt = now
    }

    fun isAvailable(now: LocalDateTime): Boolean {
        return revokedAt == null && expiresAt.isAfter(now)
    }
}
