package com.wheon.ourrecord.domain.couple

import com.wheon.ourrecord.core.enums.CoupleInviteState
import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Service

@Service
class CoupleInviteService(
    private val coupleInviteReader: CoupleInviteReader,
    private val coupleInviteManager: CoupleInviteManager,
    private val coupleInvitePolicyValidator: CoupleInvitePolicyValidator,
    private val coupleInviteFinder: CoupleInviteFinder,
) {
    fun createInvite(apiUser: ApiUser, newCoupleInvite: NewCoupleInvite): String {
        coupleInvitePolicyValidator.validateNew(apiUser.id)
        return coupleInviteManager.create(apiUser.id, newCoupleInvite)
    }

    fun getInviteByInvite(inviteKey: String): CoupleInvite {
        return coupleInviteReader.getInviteByInviteKey(inviteKey, CoupleInviteState.CREATED)
    }

    fun acceptInvite(apiUser: ApiUser, inviteKey: String, partnerProfile: NewPartnerProfile): Long {
        coupleInvitePolicyValidator.validateAccept(apiUser.id, inviteKey)
        return coupleInviteManager.accept(apiUser.id, inviteKey, partnerProfile)
    }

    fun findPendingUserInvite(apiUser: ApiUser): UserPendingInvite {
        return coupleInviteFinder.findPendingInviteByOwner(apiUser.id)
    }
}
