package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.AuthKeyState
import com.wheon.ourrecord.core.enums.AuthKeyType
import org.springframework.data.jpa.repository.JpaRepository

interface AuthKeyRepository : JpaRepository<AuthKeyEntity, Long> {
    fun findByKeyAndType(key: String, type: AuthKeyType): AuthKeyEntity?
    fun findByUserIdAndSessionIdAndKeyAndType(userId: Long, sessionId: Long, key: String, type: AuthKeyType): AuthKeyEntity?
    fun findByUserIdAndSessionIdAndTypeAndState(userId: Long, sessionId: Long, type: AuthKeyType, state: AuthKeyState): List<AuthKeyEntity>
}
