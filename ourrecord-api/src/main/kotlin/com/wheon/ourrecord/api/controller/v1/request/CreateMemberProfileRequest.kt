package com.wheon.ourrecord.api.controller.v1.request

import com.wheon.ourrecord.domain.NewMemberProfile

data class CreateMemberProfileRequest(
    val displayName: String,
    val emoji: String,
) {
    fun toNewMemberProfile() = NewMemberProfile(
        displayName = displayName,
        emoji = emoji,
    )
}
