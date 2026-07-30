package com.wheon.ourrecord.core.domain.member

data class Member(
    val id: Long,
    val spaceId: Long,
    val userId: Long,
    val nickname: String,
    val emoji: String,
)
