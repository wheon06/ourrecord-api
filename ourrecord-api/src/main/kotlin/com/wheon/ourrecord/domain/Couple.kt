package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.CoupleState
import java.time.LocalDate

data class Couple(
    val id: Long,
    val state: CoupleState,
    val anniversaryDate: LocalDate,
    val ownerId: Long,
)
