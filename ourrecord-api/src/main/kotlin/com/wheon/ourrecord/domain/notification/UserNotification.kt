package com.wheon.ourrecord.domain.notification

import java.time.LocalDateTime

data class UserNotification(
    val id: Long,
    val toUserId: Long,
    val type: String,
    val metadata: Map<String, String>,
    val isRead: Boolean,
    val createdAt: LocalDateTime,
)
