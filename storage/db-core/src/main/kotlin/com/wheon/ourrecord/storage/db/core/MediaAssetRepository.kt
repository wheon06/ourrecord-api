package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.MediaAssetState
import org.springframework.data.jpa.repository.JpaRepository

interface MediaAssetRepository : JpaRepository<MediaAssetEntity, Long> {
    fun findByCoupleIdAndIdInAndStateAndStatus(coupleId: Long, ids: List<Long>, state: MediaAssetState, status: EntityStatus): List<MediaAssetEntity>
}
