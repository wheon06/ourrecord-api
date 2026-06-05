package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.EntityStatus
import org.springframework.data.jpa.repository.JpaRepository

interface CoupleRepository : JpaRepository<CoupleEntity, Long> {
    fun findByIdAndStatus(id: Long, status: EntityStatus): CoupleEntity?
}
