package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "couple")
class CoupleEntity(
    val publicId: String,
    val createdFromInviteId: Long,
    val anniversaryDate: LocalDate,
    @Enumerated(EnumType.STRING)
    val state: CoupleState,
    val endedAt: LocalDateTime?,
) : BaseEntity()
