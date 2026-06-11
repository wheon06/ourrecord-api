package com.wheon.ourrecord.domain

import com.wheon.ourrecord.support.ApiUser
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDeviceManager: UserDeviceManager,
) {
    fun updateCurrentDevice(apiUser: ApiUser, pushToken: String) {
        userDeviceManager.updateCurrentPushToken(
            userId = apiUser.id,
            pushToken = pushToken,
        )
    }
}
