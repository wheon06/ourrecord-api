package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.domain.couple.CoupleInvite
import com.wheon.ourrecord.core.enums.CoupleInviteState
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
