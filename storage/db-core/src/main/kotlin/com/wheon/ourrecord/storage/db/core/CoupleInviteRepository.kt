package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface CoupleInviteRepository : JpaRepository<CoupleInviteEntity, Long> {
    fun findByInviteKeyAndStatus(inviteKey: String, status: EntityStatus): CoupleInviteEntity?
}
