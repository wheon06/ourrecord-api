package com.wheon.ourrecord.domain.letter

import java.time.LocalDate
import java.time.LocalDateTime

data class Letter(
    val id: Long,
    val senderName: String,
    val content: String,
    val revealDate: LocalDate,
    val createdAt: LocalDateTime,
    val coupleId: Long,
    val senderUserId: Long,
)
