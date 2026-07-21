package com.wheon.ourrecord.core.domain.couple

import java.time.LocalDate

data class NewCoupleInvite(
    val anniversaryDate: LocalDate,
    val ownerDisplayName: String,
    val ownerEmoji: String,
)
