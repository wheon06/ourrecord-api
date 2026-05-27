package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleMemberRole
import com.wheon.ourrecord.core.enums.CoupleMemberState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "couple_member",
    indexes = [
        Index(name = "udx_couple_member_user_id_couple_id", columnList = "userId, coupleId", unique = true),
    ],
)
class CoupleMemberEntity(
    val coupleId: Long,
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val state: CoupleMemberState,
    @Enumerated(EnumType.STRING)
    val role: CoupleMemberRole,
    val displayName: String,
    val emoji: String,
    val leftAt: LocalDateTime?,
) : BaseEntity()
