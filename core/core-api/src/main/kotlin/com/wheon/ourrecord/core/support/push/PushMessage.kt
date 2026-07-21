package com.wheon.ourrecord.core.support.push

data class PushMessage(
    val token: String,
    val title: String,
    val body: String,
    val data: Map<String, String>,
)
