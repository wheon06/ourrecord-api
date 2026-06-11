package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.core.enums.RecordState
import java.time.LocalDate

data class Record(
    val id: Long,
    val coupleId: Long,
    val authorMemberId: Long,
    val couplePlaceId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val state: RecordState,
    val media: List<RecordMedia>,
)
