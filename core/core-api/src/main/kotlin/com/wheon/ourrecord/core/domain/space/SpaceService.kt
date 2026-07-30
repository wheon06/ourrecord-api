package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.domain.member.MemberProfile
import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service

@Service
class SpaceService(
    private val spaceInviteManager: SpaceInviteManager,
) {
    fun createInvite(user: User, profile: MemberProfile): String {
        return spaceInviteManager.create(user.id, profile)
    }

    fun acceptInvite(user: User, inviteKey: String) {
        spaceInviteManager.accept(user.id, inviteKey)
    }
}
