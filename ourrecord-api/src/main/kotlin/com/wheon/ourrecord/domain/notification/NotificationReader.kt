package com.wheon.ourrecord.domain.notification

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.NotificationState
import com.wheon.ourrecord.storage.db.core.NotificationEntity
import com.wheon.ourrecord.storage.db.core.NotificationRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import tools.jackson.databind.ObjectMapper

@Component
class NotificationReader(
    private val notificationRepository: NotificationRepository,
    private val objectMapper: ObjectMapper,
) {
    fun getNotifications(coupleId: Long, userId: Long): List<UserNotification> {
        return notificationRepository.findByToUserIdAndCoupleIdAndStatusOrderByCreatedAtDesc(
            toUserId = userId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
            pageable = PageRequest.of(0, NOTIFICATION_LIST_SIZE),
        ).map { it.toUserNotification() }
    }

    private fun NotificationEntity.toUserNotification(): UserNotification {
        return UserNotification(
            id = id,
            toUserId = toUserId,
            type = NotificationTypeMapper.toApiValue(type),
            metadata = readMetadata(payloadJson),
            isRead = state == NotificationState.READ,
            createdAt = createdAt,
        )
    }

    private fun readMetadata(payloadJson: String): Map<String, String> {
        return runCatching {
            val type = objectMapper.typeFactory.constructMapType(
                Map::class.java,
                String::class.java,
                String::class.java,
            )
            objectMapper.readValue(payloadJson, type) as Map<String, String>
        }.getOrDefault(emptyMap())
    }

    companion object {
        private const val NOTIFICATION_LIST_SIZE = 50
    }
}
