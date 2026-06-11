package com.wheon.ourrecord.domain.notification

import com.wheon.ourrecord.core.enums.NotificationType
import com.wheon.ourrecord.support.error.ApiException
import com.wheon.ourrecord.support.error.ErrorType

object NotificationTypeMapper {
    fun fromApiValue(value: String): NotificationType {
        return when (value) {
            "record_added" -> NotificationType.RECORD_ADD
            "couple_connected" -> NotificationType.COUPLE_CONNECTED
            "letter_received" -> NotificationType.LETTER_RECEIVED
            else -> throw ApiException(ErrorType.INVALID_REQUEST)
        }
    }

    fun toApiValue(type: NotificationType): String {
        return when (type) {
            NotificationType.RECORD_ADD -> "record_added"
            NotificationType.COUPLE_CONNECTED -> "couple_connected"
            NotificationType.LETTER_RECEIVED -> "letter_received"
        }
    }
}
