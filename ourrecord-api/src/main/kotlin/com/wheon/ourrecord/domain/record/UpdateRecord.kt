package com.wheon.ourrecord.domain.record

import com.wheon.ourrecord.domain.place.AddPlace
import java.time.LocalDate

data class UpdateRecord(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val place: AddPlace,
    val imageIds: List<Long>,
)
