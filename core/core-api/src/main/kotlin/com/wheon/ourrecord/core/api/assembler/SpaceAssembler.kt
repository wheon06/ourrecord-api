package com.wheon.ourrecord.core.api.assembler

import com.wheon.ourrecord.core.api.controller.v1.response.InviteCheckoutResponse
import com.wheon.ourrecord.core.api.controller.v1.response.SpaceMeResponse
import com.wheon.ourrecord.core.domain.member.MemberService
import com.wheon.ourrecord.core.domain.space.SpaceService
import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Component

@Component
class SpaceAssembler(
    private val spaceService: SpaceService,
    private val memberService: MemberService,
) {
    fun getMe(user: User): SpaceMeResponse {
        val member = memberService.getMember(user)
        val space = spaceService.getSpace(member.spaceId)
        val members = memberService.getSpaceMembers(member.spaceId)
        return SpaceMeResponse.of(
            user = user,
            space = space,
            members = members,
        )
    }

    fun getInvite(inviteKey: String): InviteCheckoutResponse {
        val invite = spaceService.getInvite(inviteKey)
        val member = memberService.getMember(User(invite.userId))
        return InviteCheckoutResponse(inviteKey, member.nickname)
    }
}
