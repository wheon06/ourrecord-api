package com.wheon.ourrecord.domain

import java.time.LocalDate

data class NewCoupleInvite(
    val anniversaryDate: LocalDate,
    val ownerDisplayName: String,
    val ownerEmoji: String,
)
