package com.wheon.ourrecord.api.controller.v1.request

import com.wheon.ourrecord.domain.NewPartnerProfile

data class CreateMemberProfileRequest(
    val displayName: String,
    val emoji: String,
) {
    fun toNewPartnerProfile() = NewPartnerProfile(
        displayName = displayName,
        emoji = emoji,
    )
}
