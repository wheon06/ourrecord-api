package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.MemberRepository
import com.wheon.ourrecord.storage.db.core.SpaceRepository
import org.springframework.stereotype.Component

@Component
class SpaceVerifier(
    private val spaceRepository: SpaceRepository,
    private val memberRepository: MemberRepository,
) {
    fun verify(userId: Long, spaceId: Long): SpaceContext {
        spaceRepository.findByIdAndStatus(spaceId, EntityStatus.ACTIVE)
            ?: throw CoreException(ErrorType.NOT_FOUND_DATA)

        val member = memberRepository.findBySpaceIdAndUserId(spaceId, userId)
            ?: throw CoreException(ErrorType.NOT_FOUND_DATA)

        return SpaceContext(
            spaceId = spaceId,
            memberId = member.id,
        )
    }
}
