package com.wheon.ourrecord.core.domain.record

import java.time.LocalDate

data class RecordContent(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
)
