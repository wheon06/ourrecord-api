package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.NotificationState
import com.wheon.ourrecord.core.enums.NotificationType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "notification")
class NotificationEntity(
    val toUserId: Long,
    val fromUserId: Long,
    val coupleId: Long,
    @Enumerated(EnumType.STRING)
    val state: NotificationState,
    @Enumerated(EnumType.STRING)
    val type: NotificationType,
    val payloadJson: String,
    val readAt: Long?,
) : BaseEntity()
