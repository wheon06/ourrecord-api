package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleInviteState
import com.wheon.ourrecord.core.enums.EntityStatus
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface CoupleInviteRepository : JpaRepository<CoupleInviteEntity, Long> {
    fun findByInviteKeyAndStateAndStatus(inviteKey: String, state: CoupleInviteState, status: EntityStatus): CoupleInviteEntity?

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
        """
        SELECT c 
        FROM CoupleInviteEntity c
        WHERE c.inviteKey = :inviteKey 
            AND c.state = :state
            AND c.status = :status
    """,
    )
    fun findByInviteKeyAndStateAndStatusForUpdate(inviteKey: String, state: CoupleInviteState, status: EntityStatus): CoupleInviteEntity?

    fun findByOwnerUserIdAndStatus(
        ownerUserId: Long,
        status: EntityStatus,
    ): CoupleInviteEntity?
}
