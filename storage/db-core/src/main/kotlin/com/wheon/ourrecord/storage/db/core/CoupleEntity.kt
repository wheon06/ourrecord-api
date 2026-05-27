package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "couple")
class CoupleEntity(
    val anniversaryDate: LocalDate,
    val createdByUserId: Long,
    val closedAt: LocalDateTime?,
) : BaseEntity()
