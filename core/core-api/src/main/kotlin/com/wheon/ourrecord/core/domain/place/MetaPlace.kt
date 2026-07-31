package com.wheon.ourrecord.core.domain.place

import java.time.LocalDate

data class MetaPlace(
    val placeId: Long,
    val recordCount: Int,
    val lastVisitedAt: LocalDate,
)
