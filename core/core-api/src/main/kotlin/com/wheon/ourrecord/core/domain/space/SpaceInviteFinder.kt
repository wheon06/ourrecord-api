package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.enums.SpaceInviteState
import com.wheon.ourrecord.core.support.error.CoreException
import com.wheon.ourrecord.core.support.error.ErrorType
import com.wheon.ourrecord.storage.db.core.SpaceInviteRepository
import org.springframework.stereotype.Component

@Component
class SpaceInviteFinder(
    private val spaceInviteRepository: SpaceInviteRepository,
) {
    fun find(inviteKey: String): SpaceInvite {
        val invite = spaceInviteRepository.findByInviteKey(inviteKey) ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        if (invite.state != SpaceInviteState.PENDING) throw CoreException(ErrorType.ALREADY_JOINED_COUPLE)
        return SpaceInvite(
            inviteKey = invite.inviteKey,
            userId = invite.userId,
            state = invite.state,
        )
    }
    fun find(userId: Long): SpaceInvite {
        val invite = spaceInviteRepository.findByUserId(userId) ?: throw CoreException(ErrorType.NOT_FOUND_DATA)
        return SpaceInvite(
            inviteKey = invite.inviteKey,
            userId = invite.userId,
            state = invite.state,
        )
    }
}
