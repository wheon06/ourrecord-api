package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.CoupleInviteState
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleInviteEntity
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class CoupleInviteFinder(
    private val coupleInviteRepository: CoupleInviteRepository,
) {
    fun hasInviteHistory(userId: Long): Boolean {
        return coupleInviteRepository.existsByOwnerUserIdAndStatus(userId, EntityStatus.ACTIVE) ||
            coupleInviteRepository.existsByAcceptedByUserIdAndStateAndStatus(
                acceptedByUserId = userId,
                state = CoupleInviteState.ACCEPTED,
                status = EntityStatus.ACTIVE,
            )
    }

    fun findPendingInviteByOwner(userId: Long): UserPendingInvite {
        val coupleInvite = coupleInviteRepository.findByOwnerUserIdAndStateAndStatus(
            ownerUserId = userId,
            state = CoupleInviteState.CREATED,
            status = EntityStatus.ACTIVE,
        ) ?: return UserPendingInvite.None

        return UserPendingInvite.Waiting(coupleInvite.toCoupleInvite())
    }

    fun find(userId: Long): CoupleInvite {
        val coupleInvite = coupleInviteRepository.findByOwnerUserIdAndStatus(userId, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        return coupleInvite.toCoupleInvite()
    }

    private fun CoupleInviteEntity.toCoupleInvite(): CoupleInvite {
        return CoupleInvite(
            inviteKey = inviteKey,
            anniversaryDate = anniversaryDate,
            ownerUserId = ownerUserId,
            ownerDisplayName = ownerDisplayName,
            ownerEmoji = ownerEmoji,
            state = state,
        )
    }
}
