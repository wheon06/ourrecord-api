package com.wheon.ourrecord.core.domain.record

import java.time.LocalDate

data class Record(
    val id: Long,
    val placeId: Long,
    val thumbnailUrl: String,
    val visitedOn: LocalDate,
)
