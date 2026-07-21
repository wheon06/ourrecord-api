package com.wheon.ourrecord.domain.record

import java.time.LocalDate

data class NewRecord(
    val coupleId: Long,
    val authorMemberId: Long,
    val couplePlaceId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
)
