package com.wheon.ourrecord.api.controller.v1.request

data class CreateNotificationRequest(
    val toUserId: Long,
    val type: String,
    val metadata: Map<String, String> = emptyMap(),
)
