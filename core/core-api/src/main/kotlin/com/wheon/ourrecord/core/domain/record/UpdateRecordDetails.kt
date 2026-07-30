package com.wheon.ourrecord.core.domain.record

import java.time.LocalDate

data class UpdateRecordDetails(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
)
