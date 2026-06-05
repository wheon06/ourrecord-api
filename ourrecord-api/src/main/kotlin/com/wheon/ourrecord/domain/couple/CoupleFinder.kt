package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import org.springframework.stereotype.Component

@Component
class CoupleFinder(
    private val coupleMemberRepository: CoupleMemberRepository,
    private val coupleReader: CoupleReader,
) {
    fun findUserCouple(userId: Long): UserCouple {
        val member = coupleMemberRepository.findByUserId(userId).singleOrNull { it.isActive() } ?: return UserCouple.None
        val couple = coupleReader.getCouple(member.coupleId)
        return UserCouple.Joined(couple)
    }
}
