package com.wheon.ourrecord.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface RecordRepository : JpaRepository<RecordEntity, Long> {
    fun findBySpaceIdAndIdGreaterThanOrderByCreatedAtDesc(spaceId: Long, id: Long?): List<RecordEntity>
}
