package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.CoupleState
import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Service

@Service
class CoupleService(
    private val coupleReader: CoupleReader,
    private val coupleManager: CoupleManager,
    private val coupleInviteReader: CoupleInviteReader,
    private val coupleInviteManager: CoupleInviteManager,
) {
    fun create(apiUser: ApiUser, newCouple: NewCouple): Long {
        return coupleManager.create(apiUser.id, newCouple)
    }

    fun createInvite(apiUser: ApiUser, coupleId: Long): String {
        val couple = coupleReader.getCouple(apiUser.id, coupleId, CoupleState.CREATED)
        return coupleInviteManager.create(couple.id)
    }

    fun getCoupleByInvite(inviteKey: String): Couple {
        return coupleInviteReader.getCoupleByInviteKey(inviteKey)
    }
}
