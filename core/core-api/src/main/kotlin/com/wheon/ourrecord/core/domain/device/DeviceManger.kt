package com.wheon.ourrecord.core.domain

import com.wheon.ourrecord.core.enums.PlatformType
import com.wheon.ourrecord.storage.db.core.UserDeviceEntity
import com.wheon.ourrecord.storage.db.core.UserDeviceRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class DeviceManger(
    private val userDeviceRepository: UserDeviceRepository,
) {
    @Transactional
    fun loginDevice(userId: Long, pushKey: String, platform: PlatformType) {
        userDeviceRepository.findByPushKey(pushKey)?.bindUser(userId)
            ?: userDeviceRepository.save(
                UserDeviceEntity(
                    userId = userId,
                    platform = platform,
                    pushKey = pushKey,
                    lastSeenAt = LocalDateTime.now(),
                )
            )
    }

    @Transactional
    fun remove(pushKey: String) {
        userDeviceRepository.deleteByPushKey(pushKey)
    }
}
