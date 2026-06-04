package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.CoupleMemberRole
import com.wheon.ourrecord.core.enums.CoupleState
import com.wheon.ourrecord.storage.db.core.CoupleMemberEntity
import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import org.springframework.stereotype.Component

@Component
class CoupleMemberManager(
    private val coupleMemberRepository: CoupleMemberRepository,
    private val coupleReader: CoupleReader,
) {
    fun add(userId: Long, coupleId: Long, newMemberProfile: NewMemberProfile): Long {
        val couple = coupleReader.getCouple(userId, coupleId, CoupleState.CREATED)
        val addedMember = coupleMemberRepository.save(
            CoupleMemberEntity(
                coupleId = coupleId,
                userId = userId,
                role = if (couple.ownerId == userId) CoupleMemberRole.OWNER else CoupleMemberRole.MEMBER,
                displayName = newMemberProfile.displayName,
                emoji = newMemberProfile.emoji,
            ),
        )
        return addedMember.id
    }
}
