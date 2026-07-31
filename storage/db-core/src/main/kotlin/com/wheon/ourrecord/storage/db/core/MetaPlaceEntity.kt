package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "meta_place")
class MetaPlaceEntity(
    val spaceId: Long,
    val placeId: Long,
    recordCount: Int,
    lastVisitedAt: LocalDate,
) : BaseEntity() {
    var recordCount: Int = recordCount
        protected set

    var lastVisitedAt: LocalDate = lastVisitedAt
        protected set

    fun applyRecordCount(recordCount: Int) {
        this.recordCount = recordCount
    }

    fun applyLastVisitedAt(lastVisitedAt: LocalDate) {
        this.lastVisitedAt = lastVisitedAt
    }
}
