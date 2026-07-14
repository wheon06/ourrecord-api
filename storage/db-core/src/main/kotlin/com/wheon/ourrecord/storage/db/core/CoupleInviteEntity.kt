package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.CoupleInviteState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "couple_invite",
    indexes = [
        Index(name = "udx_couple_invite_key", columnList = "inviteKey", unique = true),
    ]
)
class CoupleInviteEntity(
    val inviteKey: String,
    val userId: Long,
    state: CoupleInviteState,
) : BaseNoStatusEntity() {
    @Enumerated(EnumType.STRING)
    var state: CoupleInviteState = state
        protected set

    fun accepted() {
        state = CoupleInviteState.ACCEPTED
    }
}
