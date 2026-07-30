package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.place.NewPlace
import java.time.LocalDate

data class UpdateRecord(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val place: NewPlace,
    val imageIds: List<Long>,
)
