package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface SpacePlaceRepository : JpaRepository<SpacePlaceEntity, Long> {
    fun findByIdAndSpaceIdAndStatus(id: Long, spaceId: Long, status: EntityStatus): SpacePlaceEntity?
}
