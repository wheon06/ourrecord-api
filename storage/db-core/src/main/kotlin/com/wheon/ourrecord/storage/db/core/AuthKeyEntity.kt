package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.AuthKeyState
import com.wheon.ourrecord.core.enums.AuthKeyType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "auth_key",
    indexes = [
        Index(name = "udx_auth_key", columnList = "key, type", unique = true),
    ],
)
class AuthKeyEntity(
    val userId: Long,
    val sessionId: Long,
    val key: String,
    val issuedAt: LocalDateTime,
    expiresAt: LocalDateTime,
    state: AuthKeyState = AuthKeyState.ACTIVE,

    @Enumerated(EnumType.STRING)
    val type: AuthKeyType,
) : BaseIdEntity() {
    @Enumerated(EnumType.STRING)
    var state: AuthKeyState = state
        protected set

    var expiresAt: LocalDateTime = expiresAt
        protected set

    fun revoke() {
        state = AuthKeyState.REVOKED
        expiresAt = LocalDateTime.now()
    }

    fun isActive(): Boolean {
        return state == AuthKeyState.ACTIVE && expiresAt.isAfter(LocalDateTime.now())
    }
}
