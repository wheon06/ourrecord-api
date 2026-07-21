package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.core.enums.CoupleInviteState
import java.time.LocalDateTime

data class CoupleInvite(
    val inviteKey: String,
    val userId: Long,
    val state: CoupleInviteState,
    val createdAt: LocalDateTime,
)
