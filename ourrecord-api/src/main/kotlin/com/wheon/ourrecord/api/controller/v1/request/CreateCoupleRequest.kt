package com.wheon.ourrecord.api.controller.v1.request

import com.wheon.ourrecord.domain.NewCouple
import java.time.LocalDate

data class CreateCoupleRequest(
    val anniversaryDate: LocalDate,
) {
    fun toNewCouple() = NewCouple(
        anniversaryDate = anniversaryDate,
    )
}
