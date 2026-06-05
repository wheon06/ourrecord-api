package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.CoupleInviteState
import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component

@Component
class CoupleInviteReader(
    private val coupleInviteRepository: CoupleInviteRepository,
) {
    fun getInviteByInviteKey(inviteKey: String, state: CoupleInviteState): CoupleInvite {
        val found = coupleInviteRepository.findByInviteKeyAndStateAndStatus(inviteKey, state, EntityStatus.ACTIVE)
            ?: throw ApiException(ErrorType.INVALID_INVITE_KEY)
        return CoupleInvite(
            inviteKey = found.inviteKey,
            ownerUserId = found.ownerUserId,
            anniversaryDate = found.anniversaryDate,
            ownerDisplayName = found.ownerDisplayName,
            ownerEmoji = found.ownerEmoji,
            state = found.state,
        )
    }
}
