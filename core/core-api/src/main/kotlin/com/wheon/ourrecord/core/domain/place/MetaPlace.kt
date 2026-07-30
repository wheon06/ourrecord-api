package com.wheon.ourrecord.core.domain.place

import java.time.LocalDateTime

data class MetaPlace(
    val placeId: Long,
    val recordCount: Int,
    val lastRecordedAt: LocalDateTime,
)
