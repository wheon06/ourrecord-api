package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface RecordMediaRepository : JpaRepository<RecordMediaEntity, Long> {
    fun findByRecordIdAndStatus(recordId: Long, status: EntityStatus): List<RecordMediaEntity>
    fun findByMediaUrlIn(mediaUrls: List<String>): List<RecordMediaEntity>
    fun findByRecordIdIn(recordIds: List<Long>): List<RecordMediaEntity>
}
