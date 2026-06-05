package com.wheon.ourrecord.api.assembler

import com.wheon.ourrecord.api.controller.v1.response.UserMeResponse
import com.wheon.ourrecord.domain.CoupleInviteService
import com.wheon.ourrecord.domain.CoupleService
import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Component

@Component
class UserAssembler(
    private val coupleService: CoupleService,
    private val coupleInviteService: CoupleInviteService,
) {
    fun getUserMe(apiUser: ApiUser): UserMeResponse {
        val userCouple = coupleService.findUserCouple(apiUser)
        val coupleInvite = coupleInviteService.findUserInvite(apiUser)
        return UserMeResponse.of(apiUser.id, userCouple, coupleInvite)
    }
}
