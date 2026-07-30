package com.wheon.ourrecord.core.api.controller.v1.request

import com.wheon.ourrecord.core.domain.member.MemberProfile
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType

data class ModifyMemberProfileRequest(
    val nickname: String,
    val emoji: String,
) {
    fun toMemberProfile(): MemberProfile {
        if (nickname.length > 20) throw CoreException(ErrorType.INVALID_REQUEST)
        return MemberProfile(
            nickname = nickname,
            emoji = emoji,
        )
    }
}
