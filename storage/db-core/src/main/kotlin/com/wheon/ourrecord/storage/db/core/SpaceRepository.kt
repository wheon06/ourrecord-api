package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface SpaceRepository : JpaRepository<SpaceEntity, Long> {
    fun findByIdAndStatus(id: Long, status: EntityStatus): SpaceEntity?
}
