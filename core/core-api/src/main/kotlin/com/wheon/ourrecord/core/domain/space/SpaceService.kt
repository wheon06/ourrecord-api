package com.wheon.ourrecord.core.domain.space

import com.wheon.ourrecord.core.domain.user.User
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SpaceService(
    private val spaceFinder: SpaceFinder,
    private val spaceInviteManager: SpaceInviteManager,
    private val spaceInviteFinder: SpaceInviteFinder,
    private val spaceManager: SpaceManager,
) {
    fun getSpace(spaceId: Long): Space {
        return spaceFinder.find(spaceId)
    }

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

    fun applyAnniversaryDate(user: User, date: LocalDate) {
        spaceManager.applyAnniversaryDate(user.id, date)
    }
}
