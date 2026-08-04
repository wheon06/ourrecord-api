package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface MetaPlaceRepository : JpaRepository<MetaPlaceEntity, Long> {
    fun findBySpaceIdAndPlaceIdIn(spaceId: Long, placeIds: List<Long>): List<MetaPlaceEntity>
    fun findByPlaceIdAndStatus(placeId: Long, status: EntityStatus): MetaPlaceEntity?
    fun findBySpaceIdAndStatusOrderByLastVisitedAtDesc(spaceId: Long, status: EntityStatus, pageable: Pageable): Slice<MetaPlaceEntity>
}
