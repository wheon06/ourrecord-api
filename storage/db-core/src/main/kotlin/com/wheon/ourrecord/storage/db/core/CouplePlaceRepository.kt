package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface CouplePlaceRepository : JpaRepository<CouplePlaceEntity, Long> {
    fun findByCoupleIdAndPlaceId(coupleId: Long, placeId: Long): CouplePlaceEntity?
    fun findByIdAndCoupleIdAndStatus(id: Long, coupleId: Long, status: EntityStatus): CouplePlaceEntity?
}
