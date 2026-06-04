package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import com.wheon.ourrecord.storage.db.core.CoupleRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CoupleInviteReader(
    private val coupleInviteRepository: CoupleInviteRepository,
    private val coupleRepository: CoupleRepository,
) {
    fun getCoupleInvite(inviteKey: String): CoupleInvite {
        val found = coupleInviteRepository.findByInviteKeyAndStatus(inviteKey, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.INVALID_INVITE_KEY)
        return CoupleInvite(
            coupleId = found.coupleId,
            inviteKey = found.inviteKey,
        )
    }

    fun getCoupleByInviteKey(inviteKey: String): Couple {
        val invite = getCoupleInvite(inviteKey)
        val coupleEntity = coupleRepository.findByIdOrNull(invite.coupleId)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        if (coupleEntity.isDeleted()) throw ApiException(ErrorType.NOT_FOUND_DATA)

        return Couple(
            id = coupleEntity.id,
            state = coupleEntity.state,
            anniversaryDate = coupleEntity.anniversaryDate,
            ownerId = coupleEntity.ownerId,
        )
    }
}
