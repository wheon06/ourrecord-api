package com.wheon.ourrecord.core.domain.member

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.MemberRepository
import org.springframework.stereotype.Component

@Component
class MemberFinder(
    private val memberRepository: MemberRepository,
) {
    fun find(userId: Long): Member {
        val member = memberRepository.findByUserIdAndStatus(userId, EntityStatus.ACTIVE)
            ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        return Member(
            id = member.id,
            spaceId = member.spaceId,
            userId = member.userId,
            nickname = member.nickname,
            emoji = member.emoji,
        )
    }

    fun findSpaceMembers(spaceId: Long): List<Member> {
        return memberRepository.findBySpaceIdAndStatus(spaceId, EntityStatus.ACTIVE).map {
            Member(
                id = it.id,
                spaceId = it.spaceId,
                userId = it.userId,
                nickname = it.nickname,
                emoji = it.emoji,
            )
        }
    }
}
