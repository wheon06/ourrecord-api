package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.LetterState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "letter")
class LetterEntity(
    val coupleId: Long,
    val senderUserId: Long,
    @Enumerated(EnumType.STRING)
    val state: LetterState,
    val title: String,
    val content: String,
    val revealAt: LocalDateTime,
    val openedAt: LocalDateTime?,
) : BaseEntity()
