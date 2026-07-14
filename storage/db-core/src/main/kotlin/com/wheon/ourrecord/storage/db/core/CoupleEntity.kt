package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "couple")
class CoupleEntity(
    val ownerUserId: Long,
    val partnerUserId: Long,
) : BaseEntity()
