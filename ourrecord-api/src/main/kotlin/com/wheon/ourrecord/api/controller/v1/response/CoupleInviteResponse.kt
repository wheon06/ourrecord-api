package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.couple.CoupleInvite
import java.time.LocalDate

data class CoupleInviteResponse(
    val inviteKey: String,
    val anniversaryDate: LocalDate,
    val ownerDisplayName: String,
    val ownerEmoji: String,
) {
    companion object {
        fun of(
            coupleInvite: CoupleInvite,
        ): CoupleInviteResponse {
            return CoupleInviteResponse(
                inviteKey = coupleInvite.inviteKey,
                anniversaryDate = coupleInvite.anniversaryDate,
                ownerDisplayName = coupleInvite.ownerDisplayName,
                ownerEmoji = coupleInvite.ownerEmoji,
            )
        }
    }
}
