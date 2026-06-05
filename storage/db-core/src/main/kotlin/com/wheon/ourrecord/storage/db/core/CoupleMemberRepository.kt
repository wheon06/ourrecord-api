package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface CoupleMemberRepository : JpaRepository<CoupleMemberEntity, Long> {
    fun findByUserId(userId: Long): List<CoupleMemberEntity>
    fun findByCoupleIdAndStatus(coupleId: Long, status: EntityStatus): Set<CoupleMemberEntity>
}
