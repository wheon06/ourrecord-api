package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.domain.member.Member
import com.wheon.ourrecord.core.domain.user.User

data class MemberProfileResponse(
    val memberId: Long,
    val nickname: String,
    val emoji: String,
    val isMe: Boolean,
) {
    companion object {
        fun of(user: User, member: Member): MemberProfileResponse {
            return MemberProfileResponse(
                memberId = member.id,
                nickname = member.nickname,
                emoji = member.emoji,
                isMe = user.id == member.userId,
            )
        }
    }
}
