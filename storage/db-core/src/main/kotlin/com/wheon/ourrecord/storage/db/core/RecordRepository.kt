package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface RecordRepository : JpaRepository<RecordEntity, Long> {
    fun findByIdAndMemberId(id: Long, memberId: Long): RecordEntity?
    fun findBySpaceIdAndPlaceIdAndStatusOrderByVisitedOnDesc(spaceId: Long, placeId: Long, status: EntityStatus, pageable: Pageable): Slice<RecordEntity>
}
