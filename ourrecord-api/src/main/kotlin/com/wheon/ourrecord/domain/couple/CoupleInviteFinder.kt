package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class CoupleInviteFinder(
    private val coupleInviteRepository: CoupleInviteRepository,
) {
    fun find(userId: Long): CoupleInvite {
        val coupleInvite = coupleInviteRepository.findByOwnerUserIdAndStatus(userId, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        return CoupleInvite(
            inviteKey = coupleInvite.inviteKey,
            anniversaryDate = coupleInvite.anniversaryDate,
            ownerUserId = coupleInvite.ownerUserId,
            ownerDisplayName = coupleInvite.ownerDisplayName,
            ownerEmoji = coupleInvite.ownerEmoji,
            state = coupleInvite.state,
        )
    }
}
