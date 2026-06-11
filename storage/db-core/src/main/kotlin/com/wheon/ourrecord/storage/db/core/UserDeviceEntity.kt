package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.PlatformType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDateTime

@Entity
@Table(
    name = "user_device",
    uniqueConstraints = [
        UniqueConstraint(
            name = "udx_user_device_user_id_install_id",
            columnNames = ["user_id", "install_id"],
        ),
    ],
)
class UserDeviceEntity(
    val userId: Long,
    @Column(nullable = false, length = 64)
    val installId: String,
    @Enumerated(EnumType.STRING)
    val platform: PlatformType,
    pushToken: String,
    appVersion: String,
    lastSeenAt: LocalDateTime,
    revokedAt: LocalDateTime?,
) : BaseIdEntity() {
    @Column(columnDefinition = "TEXT")
    var pushToken: String = pushToken
        protected set

    var appVersion: String = appVersion
        protected set

    var lastSeenAt: LocalDateTime = lastSeenAt
        protected set

    var revokedAt: LocalDateTime? = revokedAt
        protected set

    fun touch(pushToken: String, appVersion: String, now: LocalDateTime) {
        this.pushToken = pushToken
        this.appVersion = appVersion
        this.lastSeenAt = now
        this.revokedAt = null
    }

    fun revoke(now: LocalDateTime) {
        revokedAt = now
    }
}
