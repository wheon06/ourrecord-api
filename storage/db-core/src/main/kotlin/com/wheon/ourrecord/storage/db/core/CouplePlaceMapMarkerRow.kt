package com.wheon.ourrecord.storage.db.core

import java.math.BigDecimal
import java.time.LocalDate

data class CouplePlaceMapMarkerRow(
    val couplePlaceId: Long,
    val placeId: Long,
    val categoryCode: String?,
    val name: String,
    val address: String,
    val roadAddress: String?,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val recordCount: Long,
    val latestVisitedOn: LocalDate?,
)
