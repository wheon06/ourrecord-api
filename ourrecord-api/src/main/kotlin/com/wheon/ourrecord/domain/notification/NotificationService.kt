package com.wheon.ourrecord.domain.notification

import com.wheon.ourrecord.support.ApiCoupleUser
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationManager: NotificationManager,
    private val notificationReader: NotificationReader,
) {
    fun create(apiCoupleUser: ApiCoupleUser, toUserId: Long, type: String, metadata: Map<String, String>): Long {
        return notificationManager.create(
            coupleId = apiCoupleUser.coupleId,
            fromUserId = apiCoupleUser.userId,
            toUserId = toUserId,
            type = type,
            metadata = metadata,
        )
    }

    fun getNotifications(apiCoupleUser: ApiCoupleUser): List<UserNotification> {
        return notificationReader.getNotifications(
            coupleId = apiCoupleUser.coupleId,
            userId = apiCoupleUser.userId,
        )
    }

    fun markAsRead(apiCoupleUser: ApiCoupleUser, notificationId: Long) {
        notificationManager.markAsRead(
            coupleId = apiCoupleUser.coupleId,
            userId = apiCoupleUser.userId,
            notificationId = notificationId,
        )
    }

    fun markAllAsRead(apiCoupleUser: ApiCoupleUser) {
        notificationManager.markAllAsRead(
            coupleId = apiCoupleUser.coupleId,
            userId = apiCoupleUser.userId,
        )
    }
}
