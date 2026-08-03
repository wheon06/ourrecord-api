package com.wheon.ourrecord.storage.db.core

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface RecordRepository : JpaRepository<RecordEntity, Long> {
    fun findBySpaceIdAndPlaceIdOrderByVisitedOnDesc(spaceId: Long, placeId: Long, pageable: Pageable): Slice<RecordEntity>
}
