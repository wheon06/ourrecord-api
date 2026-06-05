package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.CoupleInviteState
import java.time.LocalDate

data class CoupleInvite(
    val inviteKey: String,
    val ownerUserId: Long,
    val anniversaryDate: LocalDate,
    val ownerDisplayName: String,
    val ownerEmoji: String,
    val state: CoupleInviteState,
)
