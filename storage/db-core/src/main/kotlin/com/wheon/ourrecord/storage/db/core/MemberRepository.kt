package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long> {
    fun findBySpaceIdAndUserId(spaceId: Long, userId: Long): MemberEntity?
    fun findByUserIdAndStatus(userId: Long, status: EntityStatus): MemberEntity?
}
