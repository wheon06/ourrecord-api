package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.PlatformType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_device")
class UserDeviceEntity(
    userId: Long,
    @Enumerated(EnumType.STRING)
    val platform: PlatformType,
    @Column(columnDefinition = "TEXT")
    val pushKey: String,
    lastSeenAt: LocalDateTime,
) : BaseIdEntity() {
    var userId: Long = userId
        protected set
    var lastSeenAt: LocalDateTime = lastSeenAt
        protected set

    fun bindUser(userId: Long) {
        this.userId = userId
        this.lastSeenAt = LocalDateTime.now()
    }
}
