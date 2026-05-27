package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.InvitationState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "invitation")
class InvitationEntity(
    val coupleId: Long,
    @Enumerated(EnumType.STRING)
    val state: InvitationState,
    val codeHash: String,
    val createdByUserId: Long,
    val acceptedByUserId: Long?,
    val expiredAt: LocalDateTime,
    val acceptedAt: LocalDateTime?,
) : BaseEntity()
