package com.wheon.ourrecord.core.api.controller.v1.response

import com.wheon.ourrecord.core.domain.member.Member
import com.wheon.ourrecord.core.domain.space.Space
import com.wheon.ourrecord.core.domain.user.User
import java.time.LocalDate

data class SpaceMeResponse(
    val spaceId: Long,
    val anniversaryDate: LocalDate?,
    val members: List<MemberProfileResponse>,
) {
    companion object {
        fun of(user: User, space: Space, members: List<Member>): SpaceMeResponse {
            return SpaceMeResponse(
                spaceId = space.id,
                anniversaryDate = space.anniversaryDate,
                members = members.map {
                    MemberProfileResponse.of(
                        user = user,
                        member = it,
                    )
                },
            )
        }
    }
}
