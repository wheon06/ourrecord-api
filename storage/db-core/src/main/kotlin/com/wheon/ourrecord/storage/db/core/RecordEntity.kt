package com.wheon.ourrecord.storage.db.core

import com.wheon.ourrecord.core.enums.RecordState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "record")
class RecordEntity(
    val coupleId: Long,
    val placeId: Long,
    val authorUserId: Long,
    @Enumerated(EnumType.STRING)
    val state: RecordState,
    val visitedOn: LocalDate,
    val title: String,
    val content: String,
) : BaseEntity()
