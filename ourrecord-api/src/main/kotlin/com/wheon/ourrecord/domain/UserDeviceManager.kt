package com.wheon.ourrecord.domain

import com.wheon.ourrecord.core.enums.PlatformType
import com.wheon.ourrecord.storage.db.core.UserDeviceEntity
import com.wheon.ourrecord.storage.db.core.UserDeviceRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserDeviceManager(
    private val userDeviceRepository: UserDeviceRepository,
) {
    fun registerOrTouch(
        userId: Long,
        installId: String,
        platform: PlatformType,
        pushToken: String?,
        appVersion: String,
    ): Long {
        val now = LocalDateTime.now()
        val savedPushToken = pushToken.orEmpty()
        val device = userDeviceRepository.findByUserIdAndInstallId(userId, installId)
            ?: return userDeviceRepository.save(
                UserDeviceEntity(
                    userId = userId,
                    installId = installId,
                    platform = platform,
                    pushToken = savedPushToken,
                    appVersion = appVersion,
                    lastSeenAt = now,
                    revokedAt = null,
                ),
            ).id

        device.touch(
            pushToken = savedPushToken,
            appVersion = appVersion,
            now = now,
        )
        return userDeviceRepository.save(device).id
    }
}
