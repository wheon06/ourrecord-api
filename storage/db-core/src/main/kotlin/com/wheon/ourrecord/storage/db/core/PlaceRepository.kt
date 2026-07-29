package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<PlaceEntity, Long> {
    fun findBySpaceIdAndExternalPlaceIdAndStatus(spaceId: Long, externalPlaceId: String, status: EntityStatus): PlaceEntity?
}
