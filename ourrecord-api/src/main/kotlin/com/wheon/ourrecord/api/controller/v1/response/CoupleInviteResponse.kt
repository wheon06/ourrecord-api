package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.core.enums.CoupleInviteState
import com.wheon.ourrecord.domain.couple.CoupleInvite
import java.time.LocalDateTime

data class CoupleInviteResponse(
    val inviteKey: String,
    val userId: Long,
    val state: CoupleInviteState,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun of(access: CoupleInvite) = CoupleInviteResponse(
            inviteKey = access.inviteKey,
            userId = access.userId,
            state = access.state,
            createdAt = access.createdAt,
        )
    }
}
