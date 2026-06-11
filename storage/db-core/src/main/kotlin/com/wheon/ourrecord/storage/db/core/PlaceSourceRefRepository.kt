package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.PlaceSource
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceSourceRefRepository : JpaRepository<PlaceSourceRefEntity, Long> {
    fun findBySourceAndExternalPlaceId(source: PlaceSource, externalPlaceId: String): PlaceSourceRefEntity?
}
