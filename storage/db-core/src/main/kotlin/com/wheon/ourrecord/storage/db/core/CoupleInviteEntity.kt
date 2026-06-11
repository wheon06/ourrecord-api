package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleInviteState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(
    name = "couple_invite",
    indexes = [
        Index(name = "udx_couple_invite_invite_key", columnList = "inviteKey", unique = true),
    ],
)
class CoupleInviteEntity(
    val inviteKey: String,
    val ownerUserId: Long,
    val anniversaryDate: LocalDate,
    val ownerDisplayName: String,
    val ownerEmoji: String,
    state: CoupleInviteState,
    acceptedByUserId: Long? = null,
    acceptedAt: LocalDateTime? = null,
) : BaseEntity() {
    @Enumerated(EnumType.STRING)
    var state = state
        protected set

    var acceptedByUserId: Long? = acceptedByUserId
        protected set

    var acceptedAt: LocalDateTime? = acceptedAt
        protected set

    fun accepted(acceptedByUserId: Long) {
        this.acceptedByUserId = acceptedByUserId
        this.acceptedAt = LocalDateTime.now()
        state = CoupleInviteState.ACCEPTED
    }

    fun joined(acceptedByUserId: Long) {
        accepted(acceptedByUserId)
    }
}
