package com.wheon.ourrecord.storage.db.core

import java.time.LocalDateTime

data class LetterRow(
    val id: Long,
    val senderName: String,
    val content: String,
    val revealAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val coupleId: Long,
    val senderUserId: Long,
)
