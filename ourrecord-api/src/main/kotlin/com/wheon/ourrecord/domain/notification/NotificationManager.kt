package com.wheon.ourrecord.domain.notification

import com.wheon.ourrecord.core.enums.EntityStatus
import com.wheon.ourrecord.core.enums.NotificationState
import com.wheon.ourrecord.storage.db.core.CoupleMemberRepository
import com.wheon.ourrecord.storage.db.core.NotificationEntity
import com.wheon.ourrecord.storage.db.core.NotificationRepository
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import tools.jackson.databind.ObjectMapper

@Component
class NotificationManager(
    private val notificationRepository: NotificationRepository,
    private val coupleMemberRepository: CoupleMemberRepository,
    private val objectMapper: ObjectMapper,
) {
    @Transactional
    fun create(
        coupleId: Long,
        fromUserId: Long,
        toUserId: Long,
        type: String,
        metadata: Map<String, String>,
    ): Long {
        checkCoupleMember(
            coupleId = coupleId,
            userId = toUserId,
        )

        return notificationRepository.save(
            NotificationEntity(
                toUserId = toUserId,
                fromUserId = fromUserId,
                coupleId = coupleId,
                state = NotificationState.UNREAD,
                type = NotificationTypeMapper.fromApiValue(type),
                payloadJson = objectMapper.writeValueAsString(metadata),
                readAt = null,
            ),
        ).id
    }

    @Transactional
    fun markAsRead(coupleId: Long, userId: Long, notificationId: Long) {
        val notification = notificationRepository.findByIdAndToUserIdAndCoupleIdAndStatus(
            id = notificationId,
            toUserId = userId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
        ) ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        notification.markAsRead()
    }

    @Transactional
    fun markAllAsRead(coupleId: Long, userId: Long) {
        notificationRepository.findByToUserIdAndCoupleIdAndStatusAndState(
            toUserId = userId,
            coupleId = coupleId,
            status = EntityStatus.ACTIVE,
            state = NotificationState.UNREAD,
        ).forEach { it.markAsRead() }
    }

    private fun checkCoupleMember(coupleId: Long, userId: Long) {
        val exists = coupleMemberRepository.findByCoupleIdAndLeftAtIsNull(coupleId)
            .any { it.userId == userId }
        if (!exists) throw ApiException(ErrorType.NOT_FOUND_DATA)
    }
}
