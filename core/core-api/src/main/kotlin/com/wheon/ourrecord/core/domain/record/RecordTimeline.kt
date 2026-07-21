package com.wheon.ourrecord.domain.record

data class RecordTimeline(
    val items: List<RecordTimelineItem>,
    val nextCursor: String?,
)
