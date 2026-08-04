package com.wheon.ourrecord.core.api.assembler

import com.wheon.ourrecord.core.api.controller.v1.response.MeResponse
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.MemberRepository
import org.springframework.stereotype.Component

@Component
class UserAssembler(
    private val memberRepository: MemberRepository,
) {
    fun getMe(user: User): MeResponse {
        return MeResponse(
            userId = user.id,
            isOnboarded = memberRepository.findByUserIdAndStatus(user.id, EntityStatus.ACTIVE) != null,
        )
    }
}
