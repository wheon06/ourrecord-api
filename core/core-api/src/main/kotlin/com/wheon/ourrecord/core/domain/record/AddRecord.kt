package com.wheon.ourrecord.core.domain.record

import com.wheon.ourrecord.core.domain.place.AddPlace
import java.time.LocalDate

data class AddRecord(
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val place: AddPlace,
)
