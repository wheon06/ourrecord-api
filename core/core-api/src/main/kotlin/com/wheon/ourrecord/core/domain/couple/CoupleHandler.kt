package com.wheon.ourrecord.core.domain.couple

import com.wheon.ourrecord.core.enums.SpaceInviteState
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.SpaceInviteRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CoupleHandler(
    private val coupleInviteRepository: SpaceInviteRepository,
    private val coupleCreator: CoupleCreator,
) {
    @Transactional
    fun accept(userId: Long, inviteKey: String) {
        val invite = coupleInviteRepository.findByInviteKey(inviteKey) ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        if (invite.state != SpaceInviteState.PENDING) throw CoreException(ErrorType.INVITE_STATE_INVALID)

        invite.accepted()

        coupleCreator.create(invite.userId, userId)
    }
}
