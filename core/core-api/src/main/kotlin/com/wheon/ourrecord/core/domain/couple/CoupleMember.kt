package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.core.enums.CoupleMemberRole

data class CoupleMember(
    val id: Long,
    val userId: Long,
    val role: CoupleMemberRole,
    val displayName: String,
    val emoji: String,
)
