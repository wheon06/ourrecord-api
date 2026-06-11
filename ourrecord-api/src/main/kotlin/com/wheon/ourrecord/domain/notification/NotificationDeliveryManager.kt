package com.wheon.ourrecord.domain.notification

import com.wheon.ourrecord.core.enums.DeliveryState
import com.wheon.ourrecord.core.enums.PushProviderType
import com.wheon.ourrecord.storage.db.core.NotificationDeliveryEntity
import com.wheon.ourrecord.storage.db.core.NotificationDeliveryRepository
import com.wheon.ourrecord.storage.db.core.UserDeviceRepository
import com.wheon.ourrecord.support.push.PushMessage
import com.wheon.ourrecord.support.push.PushSender
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class NotificationDeliveryManager(
    private val userDeviceRepository: UserDeviceRepository,
    private val notificationDeliveryRepository: NotificationDeliveryRepository,
    private val pushSender: PushSender,
) {
    fun dispatch(
        notificationId: Long,
        toUserId: Long,
        type: String,
        metadata: Map<String, String>,
    ) {
        val devices = userDeviceRepository.findByUserIdAndRevokedAtIsNull(toUserId)
        if (devices.isEmpty()) return

        val template = NotificationPushTemplate.from(type, metadata)
        val data = metadata + mapOf(
            "notification_id" to notificationId.toString(),
            "type" to type,
        )

        val deliveries = devices.map { device ->
            val requestedAt = LocalDateTime.now()
            if (device.pushToken.isBlank()) {
                return@map NotificationDeliveryEntity(
                    notificationId = notificationId,
                    userDeviceId = device.id,
                    state = DeliveryState.SKIPPED,
                    provider = PushProviderType.FCM,
                    pushTokenSnapshot = "",
                    providerMessageId = null,
                    failureCode = "EMPTY_PUSH_TOKEN",
                    requestedAt = requestedAt,
                    sentAt = null,
                    failedAt = null,
                )
            }

            val result = pushSender.send(
                PushMessage(
                    token = device.pushToken,
                    title = template.title,
                    body = template.body,
                    data = data,
                ),
            )
            val completedAt = LocalDateTime.now()
            NotificationDeliveryEntity(
                notificationId = notificationId,
                userDeviceId = device.id,
                state = result.toDeliveryState(),
                provider = PushProviderType.FCM,
                pushTokenSnapshot = device.pushToken,
                providerMessageId = result.providerMessageId,
                failureCode = result.failureCode,
                requestedAt = requestedAt,
                sentAt = completedAt.takeIf { result.sent },
                failedAt = completedAt.takeIf { !result.sent && result.failureCode !in skippedFailureCodes },
            )
        }

        notificationDeliveryRepository.saveAll(deliveries)
    }

    private fun com.wheon.ourrecord.support.push.PushSendResult.toDeliveryState(): DeliveryState {
        if (sent) return DeliveryState.SENT
        if (failureCode in skippedFailureCodes) return DeliveryState.SKIPPED
        return DeliveryState.FAILED
    }

    private companion object {
        val skippedFailureCodes = setOf("PUSH_DISABLED", "FIREBASE_NOT_CONFIGURED")
    }
}
