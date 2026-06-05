package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "couple")
class CoupleEntity(
    val anniversaryDate: LocalDate,
    val ownerUserId: Long,
    val partnerUserId: Long,
) : BaseEntity()
