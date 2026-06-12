package com.wheon.ourrecord.api.assembler

import com.wheon.ourrecord.api.controller.v1.response.UserMyResponse
import com.wheon.ourrecord.domain.couple.CoupleInviteService
import com.wheon.ourrecord.domain.couple.CoupleService
import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Component

@Component
class UserAssembler(
    private val coupleService: CoupleService,
    private val coupleInviteService: CoupleInviteService,
) {
    fun getUserMy(apiUser: ApiUser): UserMyResponse {
        val userCouple = coupleService.findUserCouple(apiUser)
        val coupleInvite = coupleInviteService.findUserInvite(apiUser)
        return UserMyResponse.of(apiUser.id, userCouple, coupleInvite)
    }
}
