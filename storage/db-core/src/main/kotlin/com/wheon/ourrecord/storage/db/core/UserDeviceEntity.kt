package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.PlatformType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_device")
class UserDeviceEntity(
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val platform: PlatformType,
    val pushToken: String,
    val appVersion: String,
    val lastSeenAt: LocalDateTime,
    val revokedAt: LocalDateTime?,
) : BaseIdEntity()
