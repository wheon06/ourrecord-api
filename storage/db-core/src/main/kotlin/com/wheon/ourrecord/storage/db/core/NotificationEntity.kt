package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.NotificationState
import com.wheon.ourrecord.core.enums.NotificationType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "notification")
class NotificationEntity(
    val toUserId: Long,
    val fromUserId: Long,
    val coupleId: Long,
    state: NotificationState,
    @Enumerated(EnumType.STRING)
    val type: NotificationType,
    @field:Column(columnDefinition = "TEXT")
    val payloadJson: String,
    readAt: Long?,
) : BaseEntity() {
    @Enumerated(EnumType.STRING)
    var state: NotificationState = state
        protected set

    var readAt: Long? = readAt
        protected set

    fun markAsRead() {
        state = NotificationState.READ
        readAt = Instant.now().toEpochMilli()
    }
}
