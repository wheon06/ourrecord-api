package com.wheon.ourrecord.core.api.assembler

import com.wheon.ourrecord.core.api.controller.v1.response.InviteCheckoutResponse
import com.wheon.ourrecord.core.api.controller.v1.response.MeResponse
import com.wheon.ourrecord.core.domain.member.MemberService
import com.wheon.ourrecord.core.domain.space.SpaceService
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.MemberRepository
import org.springframework.stereotype.Component

@Component
class SpaceAssembler(
    private val spaceService: SpaceService,
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
) {
    fun getMe(user: User): MeResponse {
        return MeResponse(
            isOnboarded = memberRepository.findByUserIdAndStatus(user.id, EntityStatus.ACTIVE) != null,
        )
    }

    fun getInvite(inviteKey: String): InviteCheckoutResponse {
        val invite = spaceService.getInvite(inviteKey)
        val member = memberService.getMember(User(invite.userId))
        return InviteCheckoutResponse(inviteKey, member.nickname)
    }
}
