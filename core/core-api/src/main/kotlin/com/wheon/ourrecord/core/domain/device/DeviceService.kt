package com.wheon.ourrecord.core.domain

import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.enums.PlatformType
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val deviceManger: DeviceManger
) {
    fun loginDevice(user: User, pushKey: String, platform: PlatformType) {
        deviceManger.loginDevice(user.id, pushKey, platform)
    }

    fun deleteDevice(pushKey: String) {
        deviceManger.remove(pushKey)
    }
}
