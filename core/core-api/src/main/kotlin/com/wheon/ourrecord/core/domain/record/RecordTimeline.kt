package com.wheon.ourrecord.core.domain.record

data class RecordTimeline(
    val items: List<RecordTimelineItem>,
    val nextCursor: String?,
)
