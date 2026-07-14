package com.wheon.ourrecord.storage.db.core

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "record")
class RecordEntity(
    val userId: Long,
    val coupleId: Long,
    val couplePlaceId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
) : BaseEntity()
