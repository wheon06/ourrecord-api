package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.DeliveryState
import com.wheon.ourrecord.core.enums.PushProviderType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "notification_delivery")
class NotificationDeliveryEntity(
    val notificationId: Long,
    val userDeviceId: Long,
    @Enumerated(EnumType.STRING)
    val state: DeliveryState,
    @Enumerated(EnumType.STRING)
    val provider: PushProviderType,
    val pushTokenSnapshot: String,
    val providerMessageId: String?,
    val failureCode: String?,
    val requestedAt: LocalDateTime,
    val sentAt: LocalDateTime?,
    val failedAt: LocalDateTime?,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.MIN,
) : BaseIdEntity()
