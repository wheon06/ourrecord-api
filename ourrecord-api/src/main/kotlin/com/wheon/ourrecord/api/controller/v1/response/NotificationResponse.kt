package com.wheon.ourrecord.api.controller.v1.response

import com.wheon.ourrecord.domain.notification.UserNotification
import java.time.ZoneId

data class NotificationResponse(
    val id: Long,
    val toUserId: Long,
    val type: String,
    val metadata: Map<String, String>,
    val isRead: Boolean,
    val createdAt: String,
) {
    companion object {
        private val zoneId = ZoneId.of("Asia/Seoul")

        fun of(notifications: List<UserNotification>): List<NotificationResponse> {
            return notifications.map {
                NotificationResponse(
                    id = it.id,
                    toUserId = it.toUserId,
                    type = it.type,
                    metadata = it.metadata,
                    isRead = it.isRead,
                    createdAt = it.createdAt.atZone(zoneId).toOffsetDateTime().toString(),
                )
            }
        }
    }
}
