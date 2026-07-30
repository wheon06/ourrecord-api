package com.wheon.ourrecord.core.domain.record

import java.time.LocalDate
import java.time.LocalDateTime

data class CouplePlaceRecord(
    val recordId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val authorProfile: RecordAuthorProfile,
    val place: RecordPlace,
    val images: List<RecordImage>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
