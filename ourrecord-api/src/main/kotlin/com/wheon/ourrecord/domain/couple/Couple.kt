package com.wheon.ourrecord.domain.couple

import java.time.LocalDate

data class Couple(
    val id: Long,
    val anniversaryDate: LocalDate,
    val ownerUserMember: CoupleMember,
    val partnerUserMember: CoupleMember,
)
