package com.wheon.ourrecord.storage.db.core

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class CouplePlaceRecordRow(
    val recordId: Long,
    val title: String,
    val content: String,
    val visitedOn: LocalDate,
    val authorMemberId: Long,
    val authorDisplayName: String,
    val authorEmoji: String,
    val couplePlaceId: Long,
    val placeId: Long,
    val categoryCode: String?,
    val placeName: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
