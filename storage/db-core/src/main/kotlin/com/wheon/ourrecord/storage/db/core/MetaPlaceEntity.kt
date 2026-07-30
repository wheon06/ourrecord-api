package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "meta_place")
class MetaPlaceEntity(
    val spaceId: Long,
    val placeId: Long,
    recordCount: Int,
    lastRecordedAt: LocalDateTime,
) : BaseEntity() {
    var recordCount: Int = recordCount
        protected set

    var lastRecordedAt: LocalDateTime = lastRecordedAt
        protected set

    fun applyRecordCount(recordCount: Int) {
        this.recordCount = recordCount
    }

    fun applyLastRecordedAt(lastRecordedAt: LocalDateTime) {
        this.lastRecordedAt = lastRecordedAt
    }
}
