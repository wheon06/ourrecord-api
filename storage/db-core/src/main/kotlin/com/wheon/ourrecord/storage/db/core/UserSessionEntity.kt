package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_session")
class UserSessionEntity(
    val userId: Long,
    val deviceId: Long,
    val refreshTokenHash: String,
    val expiredAt: LocalDateTime,
    val revokedAt: LocalDateTime?,
) : BaseIdEntity()
