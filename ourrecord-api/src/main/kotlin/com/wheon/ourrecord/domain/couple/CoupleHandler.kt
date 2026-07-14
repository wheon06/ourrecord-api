package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.CoupleInviteState
import com.wheon.ourrecord.storage.db.core.CoupleInviteRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoupleHandler(
    private val coupleInviteRepository: CoupleInviteRepository,
    private val coupleCreator: CoupleCreator,
) {
    @Transactional
    fun accept(userId: Long, inviteKey: String) {
        val invite = coupleInviteRepository.findByInviteKey(inviteKey) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
        if (invite.state != CoupleInviteState.PENDING) throw ApiException(ErrorType.INVITE_STATE_INVALID)

        invite.accepted()

        coupleCreator.create(invite.userId, userId)
    }
}
