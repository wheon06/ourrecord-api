package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.CoupleMemberRole

data class CoupleMember(
    val userId: Long,
    val role: CoupleMemberRole,
    val displayName: String,
    val emoji: String,
)
