package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.SpaceInviteState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "space_invite",
    indexes = [
        Index(name = "udx_space_invite_key", columnList = "inviteKey", unique = true),
    ],
)
class SpaceInviteEntity(
    val inviteKey: String,
    val userId: Long,
    val spaceId: Long,
    state: SpaceInviteState,
) : BaseNoStatusEntity() {
    @Enumerated(EnumType.STRING)
    var state: SpaceInviteState = state
        protected set

    fun accepted() {
        state = SpaceInviteState.ACCEPTED
    }

    fun expired() {
        state = SpaceInviteState.EXPIRED
    }
}
