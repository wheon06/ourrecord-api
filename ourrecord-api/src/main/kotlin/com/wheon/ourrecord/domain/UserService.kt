package com.wheon.ourrecord.domain

import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDeviceManager: UserDeviceManager,
    private val coupleInviteFinder: CoupleInviteFinder,
) {
    fun updateCurrentDevice(apiUser: ApiUser, pushToken: String) {
        userDeviceManager.updateCurrentPushToken(
            userId = apiUser.id,
            pushToken = pushToken,
        )
    }

    fun isNewUser(apiUser: ApiUser): Boolean {
        return !coupleInviteFinder.hasInviteHistory(apiUser.id)
    }
}
