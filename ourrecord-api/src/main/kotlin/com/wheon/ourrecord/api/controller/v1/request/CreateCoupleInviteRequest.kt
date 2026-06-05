package com.wheon.ourrecord.api.controller.v1.request

import com.wheon.ourrecord.domain.NewCoupleInvite
import java.time.LocalDate

data class CreateCoupleInviteRequest(
    val anniversaryDate: LocalDate,
    val ownerDisplayName: String,
    val ownerEmoji: String,
) {
    fun toNewCoupleInvite() = NewCoupleInvite(
        anniversaryDate = anniversaryDate,
        ownerDisplayName = ownerDisplayName,
        ownerEmoji = ownerEmoji,
    )
}
