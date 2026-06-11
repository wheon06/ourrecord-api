package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.CoupleMemberRole
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import com.wheon.ourrecord.storage.db.core.CoupleRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class CoupleReader(
    private val coupleRepository: CoupleRepository,
    private val coupleMemberRepository: CoupleMemberRepository,
) {
    fun getCouple(coupleId: Long): Couple {
        val couple = coupleRepository.findByIdAndStatus(coupleId, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
        val coupleMembers = coupleMemberRepository.findByCoupleIdAndLeftAtIsNull(coupleId)

        val ownerMember = coupleMembers.find { it.role == CoupleMemberRole.OWNER } ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
        val partnerMember = coupleMembers.find { it.role == CoupleMemberRole.PARTNER } ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        return Couple(
            id = couple.id,
            anniversaryDate = couple.anniversaryDate,
            ownerUserMember = CoupleMember(
                id = ownerMember.id,
                userId = ownerMember.userId,
                role = ownerMember.role,
                displayName = ownerMember.displayName,
                emoji = ownerMember.emoji,
            ),
            partnerUserMember = CoupleMember(
                id = partnerMember.id,
                userId = partnerMember.userId,
                role = partnerMember.role,
                displayName = partnerMember.displayName,
                emoji = partnerMember.emoji,
            ),
        )
    }
}
