package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class CoupleInvitePolicyValidator(
    private val coupleInviteRepository: CoupleInviteRepository,
    private val coupleMemberRepository: CoupleMemberRepository,
) {
    fun validateNew(userId: Long) {
        val existsInvite = coupleInviteRepository.findByOwnerUserIdAndStatus(userId, EntityStatus.ACTIVE)
        if (existsInvite != null) throw ApiException(ErrorType.ALREADY_CREATED_INVITE)

        val hasCouples = coupleMemberRepository.findByUserId(userId).filter { it.isActive() }
        if (hasCouples.isNotEmpty()) {
            throw ApiException(ErrorType.ALREADY_JOINED_COUPLE)
        }
    }
}
