package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class SpaceService(
    private val spaceInviteManager: SpaceInviteManager,
    private val spaceInviteFinder: SpaceInviteFinder,
) {
    fun createInvite(user: User): String {
        return spaceInviteManager.create(user.id)
    }

    fun getInvite(inviteKey: String): SpaceInvite {
        return spaceInviteFinder.find(inviteKey)
    }

    fun getMyInvite(user: User): SpaceInvite {
        return spaceInviteFinder.find(user.id)
    }

    fun acceptInvite(user: User, inviteKey: String) {
        spaceInviteManager.accept(user.id, inviteKey)
    }
}
