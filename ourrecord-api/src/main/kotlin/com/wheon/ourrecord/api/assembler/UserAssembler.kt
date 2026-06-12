package com.wheon.ourrecord.api.assembler

import com.wheon.ourrecord.api.controller.v1.response.UserMyResponse
import com.wheon.ourrecord.domain.couple.CoupleInviteService
import com.wheon.ourrecord.domain.couple.CoupleService
import com.wheon.ourrecord.domain.couple.UserCouple
import com.wheon.ourrecord.domain.couple.UserPendingInvite
import com.wheon.ourrecord.domain.couple.UserRelationship
import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Component

@Component
class UserAssembler(
    private val coupleService: CoupleService,
    private val coupleInviteService: CoupleInviteService,
) {
    fun getUserMy(apiUser: ApiUser): UserMyResponse {
        val userCouple = coupleService.findUserCouple(apiUser)
        val relationship = when (userCouple) {
            UserCouple.None -> findWaitingRelationship(apiUser)
            is UserCouple.Joined -> UserRelationship.JoinedCouple(userCouple.couple)
        }

        return UserMyResponse.of(apiUser.id, relationship)
    }

    private fun findWaitingRelationship(apiUser: ApiUser): UserRelationship {
        return when (val pendingInvite = coupleInviteService.findPendingUserInvite(apiUser)) {
            UserPendingInvite.None -> UserRelationship.None
            is UserPendingInvite.Waiting -> UserRelationship.WaitingInvite(pendingInvite.invite)
        }
    }
}
