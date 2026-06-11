package com.wheon.ourrecord.storage.db.core

import java.time.LocalDate
import java.time.LocalDateTime

data class RecordTimelineRow(
    val recordId: Long,
    val imageId: Long,
    val sortOrder: Int,
    val bucket: String,
    val objectKey: String,
    val visitedOn: LocalDate,
    val placeName: String,
    val title: String,
    val authorMemberId: Long,
    val authorDisplayName: String,
    val authorEmoji: String,
    val createdAt: LocalDateTime,
)
