package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.domain.member.Member

data class MemberProfileResponse(
    val nickname: String,
    val emoji: String,
) {
    companion object {
        fun of(member: Member): MemberProfileResponse {
            return MemberProfileResponse(
                nickname = member.nickname,
                emoji = member.emoji,
            )
        }
    }
}
