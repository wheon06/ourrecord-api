package com.wheon.ourrecord.domain.notification

data class NotificationPushTemplate(
    val title: String,
    val body: String,
) {
    companion object {
        fun from(type: String, metadata: Map<String, String>): NotificationPushTemplate {
            return when (type) {
                "record_added" -> NotificationPushTemplate(
                    title = "새 기록이 추가됐어요",
                    body = metadata["place_name"]
                        ?.takeIf { it.isNotBlank() }
                        ?.let { "${it}에 새로운 기록이 남았어요" }
                        ?: "새로운 기록이 남았어요",
                )

                "couple_connected" -> NotificationPushTemplate(
                    title = "커플 연결 완료",
                    body = "커플이 연결됐어요",
                )

                "letter_received" -> NotificationPushTemplate(
                    title = "새 편지가 도착했어요",
                    body = "편지를 확인해보세요",
                )

                else -> NotificationPushTemplate(
                    title = "OurRecord",
                    body = "새 알림이 도착했어요",
                )
            }
        }
    }
}
