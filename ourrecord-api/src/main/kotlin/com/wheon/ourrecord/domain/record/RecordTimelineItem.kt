package com.wheon.ourrecord.domain.record

import java.time.LocalDate
import java.time.LocalDateTime

data class RecordTimelineItem(
    val recordId: Long,
    val imageId: Long,
    val sortOrder: Int,
    val photoUrl: String,
    val thumbnailUrl: String,
    val visitedOn: LocalDate,
    val placeName: String,
    val title: String,
    val authorProfile: RecordAuthorProfile,
    val createdAt: LocalDateTime,
)
