package com.wheon.ourrecord.storage.db.core

import org.springframework.data.jpa.repository.JpaRepository

interface RecordMediaRepository : JpaRepository<RecordMediaEntity, Long> {
    fun findByRecordIdIn(recordIds: List<Long>): List<RecordMediaEntity>
}
