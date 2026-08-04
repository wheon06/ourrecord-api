package com.wheon.ourrecord.core.domain.space

import java.time.LocalDate

data class Space(
    val id: Long,
    val userId: Long,
    val anniversaryDate: LocalDate?,
)
