package com.wheon.ourrecord.core.domain.member

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.MemberRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MemberManager(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun updateProfile(userId: Long, profile: MemberProfile): Long {
        val member = memberRepository.findByUserIdAndStatus(userId, EntityStatus.ACTIVE)
            ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        member.applyNickname(profile.nickname)
        member.applyEmoji(profile.emoji)
        return member.id
    }
}
