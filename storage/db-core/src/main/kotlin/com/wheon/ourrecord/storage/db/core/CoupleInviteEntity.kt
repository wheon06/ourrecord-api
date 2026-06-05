package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleInviteState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDate

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
    partnerUserId: Long? = null,
) : BaseEntity() {
    @Enumerated(EnumType.STRING)
    var state = state
        protected set

    var partnerUserId: Long? = partnerUserId
        protected set

    fun joined(partnerUserId: Long) {
        this.partnerUserId = partnerUserId
        state = CoupleInviteState.JOINED
    }
}
